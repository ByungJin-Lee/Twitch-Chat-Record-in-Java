package me.byungjin;

import me.byungjin.Managers.Chef;

public class Main {			
	public static void main(String[] args) throws Exception {	
		try {
			Chef.start();
		}catch(Exception x) {
			System.out.println(x.getMessage());
		}
	}
}