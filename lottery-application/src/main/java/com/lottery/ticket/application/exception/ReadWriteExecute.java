package com.lottery.ticket.application.exception;

	
	public class ReadWriteExecute {
	    public static int symbolicToOctal(String permString) {
	    	 String[] nums = permString.trim().split(" * ", 3);
	    	 System.out.println(nums.toString());
		/*
		 * StringBuilder sb = new StringBuilder(); for (String s : nums) { if
		 * (sb.length() > 0) sb.append(" "); int num = Integer.parseInt(s);
		 * sb.append((num & 4) == 0 ? '-' : 'r'); sb.append((num & 2) == 0 ? '-' : 'w');
		 * sb.append((num & 1) == 0 ? '-' : 'x'); }
		 */
	    	    return 0;/*Integer.valueOf(sb.toString());
	        */
	    }

	    public static void main(String[] args) {
	        // Should write 752
	        System.out.println(ReadWriteExecute.symbolicToOctal("rwxr-x-w-"));
	    }
	}


