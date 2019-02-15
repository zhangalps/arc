package com.arc.news.utils.querytools;

import com.arc.news.utils.CoreApp;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;


public class PhoneQueryTools {
	private static String[] numberType = {null, "移动", "联通", "电信", "电信虚拟运营商", "联通虚拟运营商", "移动虚拟运营商"};
	public static final int MAXCITYLEN = 25;
	private static final int INDEX_SEGMENT_LENGTH = 7;
	
	private static byte[] dataByteArray;
	private ByteBuffer byteBuffer;
	private PhoneHead head;
	
	public PhoneQueryTools() {
	    if (dataByteArray == null) {
	      synchronized (PhoneQueryTools.class) {
	        if (dataByteArray == null) {
	        	dataByteArray = loadData();
	        }
	      }
	    }
	    byteBuffer = ByteBuffer.wrap(dataByteArray);
	    byteBuffer.order(ByteOrder.BIG_ENDIAN);
	    
	    head = new PhoneHead();
	    
	    head.version = byteBuffer.getInt();
	    head.citycount = byteBuffer.getInt();
	    head.blockcount = byteBuffer.getInt();
	    head.citypos = byteBuffer.getInt();
	    head.blockpos = byteBuffer.getInt();
	  
	}
	
	public PhoneNumber lookup(String phoneNumber) {
	    if (phoneNumber == null || phoneNumber.length() > 11 || phoneNumber.length() < 7) {
	      return null;
	    }
	    int phoneHead = 0;
	    int phoneBlock = 0;
	    try {
	    	phoneHead = Integer.parseInt(phoneNumber.substring(0, 3));
	    	phoneBlock = Integer.parseInt(phoneNumber.substring(3, 7));
	    } catch (Exception e) {
	      return null;
	    }
	    
	    if(phoneHead >= head.blockcount) {
	    	return null;
	    }
	    
	    int blockpos = head.blockpos + phoneHead * 4;
	    byteBuffer.position(blockpos);
	    int indexPos = byteBuffer.getInt();
	    if(indexPos == 0) {
	    	return null;
	    }
	    byteBuffer.position(indexPos);
	    //校验一下号码应该是相同的
	    int recordHead = byteBuffer.getShort();
	    if(recordHead != phoneHead) {
	    	return null;
	    }
	    //这里是记录数
	    int phoneRecordCount = byteBuffer.getShort();
	    
	    int indexAreaOffset = indexPos + 2 + 2;
	    
	    int left = 0;
	    int right = phoneRecordCount;
	    
	    //二分查找
	    while (left <= right) {
	    	int middle = (left + right) >> 1;
	    	int currentOffset = indexAreaOffset + middle * INDEX_SEGMENT_LENGTH;
	    	if (currentOffset >= dataByteArray.length) {
	            return null;
	        }
	    	byteBuffer.position(currentOffset);
	    	short code = byteBuffer.getShort();
	    	short count = byteBuffer.getShort();
	    	if(phoneBlock < code) {
	    		right = middle - 1;
	    	}  else if (phoneBlock >= (code + count)) {
	    		left = middle + 1;
	    	} else {
	    		//这里还需要比较吗？？？
	    		//他们都是一个城市， 不再仔细比较了
	    		short cityIndex = byteBuffer.getShort();
	    		int phonetype = byteBuffer.get();
	    		
	    		int citypos = head.citypos + cityIndex * MAXCITYLEN;
	    		int cityEnd = citypos + MAXCITYLEN;
	    		while(cityEnd > citypos) {
	    			if (dataByteArray[cityEnd-1] == 0) {
	    				cityEnd--;
	    			} else
	    				break;
	    		}
	    		String infoString =
	    	            new String(dataByteArray, citypos, cityEnd - citypos, StandardCharsets.UTF_8);
	    		String[] infoSegments = infoString.split(",");
	    		
	    		PhoneNumber phone = new PhoneNumber();
	    		phone.setPhoneNumber(phoneNumber);
	    		phone.setProvince(infoSegments[0]);
	    		phone.setCity(infoSegments[1]);
	    		phone.setPhoneType(numberType[phonetype]);
	            return phone;
	    	}
	    }
	    
	    return null;
	}
	
	
	private static byte[] loadData() {
		ByteArrayOutputStream byteData = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];

        int readBytesLength;
        try {
          InputStream inputStream = CoreApp.getContext().getResources().getAssets().open("phone.dat");
          while ((readBytesLength = inputStream.read(buffer)) != -1) {
            byteData.write(buffer, 0, readBytesLength);
          }
          inputStream.close();
        } catch (Exception e) {
          System.err.println("Can't find phone.dat in classpath:phone-number/phone.dat");
          e.printStackTrace();
          throw new RuntimeException(e);
        }
        return byteData.toByteArray();
	}
	
	public static void main(String[] args) {
//		PhoneQueryTools tools = new PhoneQueryTools();
//		tools.lookup("18611358106");
//
//		String code = "13716792264";
//		int count =  1000000;
//		long start = System.currentTimeMillis();
//
//		for(int i = 0;  i< count;i++) {
//			tools.lookup(code);
//		}
//		long stop = System.currentTimeMillis();
//	    System.out.println("time===" + (stop - start)*1.0/count);
	}
}
