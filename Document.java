package com.org.transport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;


public class Document{

	private int oneCrossA;
	private int oneCrossB;
	private int threeCrossA;
	private int threeCrossB;
	private int snTime;
	private int ewTime;
	
	ArrayList<Car> CarList;
	Light t;
	static HashMap<String, Integer> prior;
	int[] One = { 2, 5 };
	int[] Three = { 1, 3, 4, 6 };
	int[] Final = { 1, 2, 3, 4, 5, 6 };

	public Document() {
		t = new Light();
		CarList = new ArrayList<Car>();
		prior = new HashMap<String, Integer>();
		snTime = Integer.parseInt(SettingGui.SNText.getText());
		ewTime = Integer.parseInt(SettingGui.EWText.getText());
		setDocumentTimer();
	}
	
	public void setDocumentTimer() {
		Timer timerOne = new Timer();
		Timer timerThree = new Timer();
		timerOne.schedule(new generateCarOne(), 0, ewTime * 1000);
		timerThree.schedule(new generateCarThree(), 0, snTime * 1000);
	}
	

	public void randomOne() {
		int index;
		Random ran = new Random();
		index = ran.nextInt(One.length);
		oneCrossA = One[index];

		Set<Integer> setOne = new HashSet<Integer>();
		setOne.add(One[index]);
		while (setOne.size() < 2) {
			index = ran.nextInt(Final.length);
			setOne.add(Final[index]);
		}
		for (Integer id : setOne) {

			if (id != oneCrossA)
				oneCrossB = id;
		}
		setOne.clear();
	}


	public void randomThree() {
		int index;
		Random ran = new Random();
		index = ran.nextInt(Three.length);
		threeCrossA = Three[index];
		Set<Integer> setThree = new HashSet<Integer>();
		setThree.add(Three[index]);
		while (setThree.size() < 2) {
			index = ran.nextInt(Final.length);
			setThree.add(Final[index]);
		}
		for (Integer id : setThree) {
			if (id != threeCrossA)
				threeCrossB = id;
		}
		setThree.clear();
	}

	private class generateCarOne extends TimerTask {
		public void run() {
			randomOne();
			if (!judgeJam(oneCrossA)) {
				Car car = new Car(oneCrossA, oneCrossB, t);
				CarList.add(car);
			}
		}
	}

	private class generateCarThree extends TimerTask {
		public void run() {
			randomThree();
			if (!judgeJam(threeCrossA)) {
				Car car = new Car(threeCrossA, threeCrossB, t);
				CarList.add(car);
			}

		}
	}

	public boolean judgeJam(int begin) {
		if (begin == 2) {
			String first = String.valueOf(Road.E.x)
					+ String.valueOf(Road.E.y);
			if (prior.containsKey(first)) {
					return true;
			}
		}
		if (begin == 5) {
			String first = String.valueOf(Road.a.x)
					+ String.valueOf(Road.a.y);
			if (prior.containsKey(first)) {
					return true;
			}
		}
		if (begin == 1) {
			String first = String.valueOf(Road.A.x)
					+ String.valueOf(Road.A.y);
			if (prior.containsKey(first)) {
					return true;
			}
		}
		if (begin == 6) {
			String first = String.valueOf(Road.O.x)
					+ String.valueOf(Road.O.y);		
			if (prior.containsKey(first)) {
					return true;
			}
		}
		if (begin == 3) {
			String first = String.valueOf(Road.N.x)
					+ String.valueOf(Road.N.y);
			if (prior.containsKey(first)) {
					return true;
			}
		}

		if (begin == 4) {
			String first = String.valueOf(Road.Z.x)
					+ String.valueOf(Road.Z.y);
			if (prior.containsKey(first)) {
					return true;
			}
		}
		return false;
	}
}
