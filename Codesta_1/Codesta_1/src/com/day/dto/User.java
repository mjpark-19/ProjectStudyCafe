package com.day.dto;

public class User {

	private String ID;
	private String PW;
	private String Cart;
	private String PaymentHistory;
	private String Point;
	private String freeADayPassTime;
	private String freeDaysPassTime;
	private String freeWeekPassLastDay;
	private String groupADayPassTime;
	private String freeWeekPassPeriod;
	private String startTime;
	private String finishTime;
	private String seatNo;
	private String roomNo;
	private String userType;
	private String freeSeatCnt;
	private String groupRoomCnt;

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public String getPW() {
		return PW;
	}

	public void setPW(String PW) {
		this.PW = PW;
	}

	public String getCart() {
		return Cart;
	}

	public void setCart(String cart) {
		Cart = cart;
	}

	public String getPaymentHistory() {
		return PaymentHistory;
	}

	public void setPaymentHistory(String paymentHistory) {
		PaymentHistory = paymentHistory;
	}

	public String getPoint() {
		return Point;
	}

	public void setPoint(String point) {
		Point = point;
	}

	public String getFreeADayPassTime() {
		return freeADayPassTime;
	}

	public void setFreeADayPassTime(String freeADayPassTime) {
		this.freeADayPassTime = freeADayPassTime;
	}

	public String getFreeDaysPassTime() {
		return freeDaysPassTime;
	}

	public void setFreeDaysPassTime(String freeDaysPassTime) {
		this.freeDaysPassTime = freeDaysPassTime;
	}

	public String getFreeWeekPassLastDay() {
		return freeWeekPassLastDay;
	}

	public void setFreeWeekPassLastDay(String freeWeekPassLastDay) {
		this.freeWeekPassLastDay = freeWeekPassLastDay;
	}

	public String getGroupADayPassTime() {
		return groupADayPassTime;
	}

	public void setGroupADayPassTime(String groupADayPassTime) {
		this.groupADayPassTime = groupADayPassTime;
	}

	public String getFreeWeekPassPeriod() {
		return freeWeekPassPeriod;
	}

	public void setFreeWeekPassPeriod(String freeWeekPassPeriod) {
		this.freeWeekPassPeriod = freeWeekPassPeriod;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getFreeSeatCnt() {
		return freeSeatCnt;
	}

	public void setFreeSeatCnt(String freeSeatCnt) {
		this.freeSeatCnt = freeSeatCnt;
	}

	public String getGroupRoomCnt() {
		return groupRoomCnt;
	}

	public void setGroupRoomCnt(String groupRoomCnt) {
		this.groupRoomCnt = groupRoomCnt;
	}

	@Override
	public String toString() {
		return "\n ID : " + getID() + "\n PW : " + getPW() + "\n Cart : " + getCart() + "\n Point : " + getPoint()
				+ "\n PaymentHistory" + getPaymentHistory() + "\n FreeADayPassTime : " + getFreeADayPassTime()
				+ "\n FreeDaysPassTime : " + getFreeDaysPassTime() + "\n FreeWeekPassLastDay : "
				+ getFreeWeekPassLastDay() + "\n GroupADayPassTime : " + getGroupADayPassTime()
				+ "\n FreeWeekPassPeriod : " + getFreeWeekPassPeriod() + "\n StartTime : " + getStartTime()
				+ "\n FinishTime : " + getFinishTime() + "\n SeatNo : " + getSeatNo() + "\n RoomNo : " + getRoomNo()
				+ "\n UserType : " + getUserType() + "\n FreeSeatCnt : " + getFreeSeatCnt() + "\n GroupRoomCnt: "
				+ getGroupRoomCnt();
	}

	// Builder Ãß°¡
	public static class Builder {
		private final String ID;
		private final String PW;
		private String Cart = "";
		private String PaymentHistory = "";
		private String Point = "";
		private String freeADayPassTime = "";
		private String freeDaysPassTime = "";
		private String freeWeekPassLastDay = "";
		private String groupADayPassTime = "";
		private String freeWeekPassPeriod = "";
		private String startTime = "";
		private String finishTime = "";
		private String seatNo = "";
		private String roomNo = "";
		private String userType = "";
		private String freeSeatCnt = "";
		private String groupRoomCnt = "";

		public Builder(String ID, String PW) {
			this.ID = ID;
			this.PW = PW;
		}

		public Builder Cart(String val) {
			Cart = val;
			return this;
		}

		public Builder PaymentHistory(String val) {
			PaymentHistory = val;
			return this;
		}

		public Builder Point(String val) {
			Point = val;
			return this;
		}

		public Builder freeADayPassTime(String val) {
			freeADayPassTime = val;
			return this;
		}

		public Builder freeDaysPassTime(String val) {
			freeDaysPassTime = val;
			return this;
		}

		public Builder freeWeekPassLastDay(String val) {
			freeWeekPassLastDay = val;
			return this;
		}

		public Builder groupADayPassTime(String val) {
			groupADayPassTime = val;
			return this;
		}

		public Builder freeWeekPassPeriod(String val) {
			freeWeekPassPeriod = val;
			return this;
		}

		public Builder startTime(String val) {
			startTime = val;
			return this;
		}

		public Builder finishTime(String val) {
			finishTime = val;
			return this;
		}

		public Builder seatNo(String val) {
			seatNo = val;
			return this;
		}

		public Builder roomNo(String val) {
			roomNo = val;
			return this;
		}

		public Builder userType(String val) {
			userType = val;
			return this;
		}

		public Builder freeSeatCnt(String val) {
			freeSeatCnt = val;
			return this;
		}

		public Builder groupRoomCnt(String val) {
			groupRoomCnt = val;
			return this;
		}

		public User build() {
			return new User(this);
		}
	}

	private User(Builder builder) {
		ID = builder.ID;
		PW = builder.PW;
		Cart = builder.Cart;
		PaymentHistory = builder.PaymentHistory;
		Point = builder.Point;
		freeADayPassTime = builder.freeADayPassTime;
		freeDaysPassTime = builder.freeDaysPassTime;
		freeWeekPassLastDay = builder.freeWeekPassLastDay;
		groupADayPassTime = builder.groupADayPassTime;
		freeWeekPassPeriod = builder.freeWeekPassPeriod;
		startTime = builder.startTime;
		finishTime = builder.finishTime;
		seatNo = builder.seatNo;
		roomNo = builder.roomNo;
		userType = builder.userType;
		freeSeatCnt = builder.freeSeatCnt;
		groupRoomCnt = builder.groupRoomCnt;
	}

}
