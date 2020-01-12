package application;

import java.util.Comparator;
import java.util.Date;

public class DataPoint implements Comparable<DataPoint> {
	private int time;
	public static int field;
	private String device, id, event, est;
	private String guest, story;

	DataPoint() {
		this.est = "";
		this.time = 0;
		this.device = "";
		this.id = "";
		this.event = "";
		this.guest = "a";
		this.field = 2;
		//this.story = this.guest + " 
	}

	public int getTime() {
		return time;
	}

	public String getDevice() {
		return device;
	}

	public String getId() {
		return id;
	}

	public String getEvent() {
		return event;
	}

	public String getEst() {
		return est;
	}

	public String getGuest() {
		return guest;
	}

	public int getField() {
		return field;
	}

	public void setField(int field) {
		this.field = field;
	}

	void setTime(int time) {
		this.time = time;
	}

	void setDevice(String device) {
		this.device = device;
	}

	void setId(String id) {
		this.id = id;
	}

	void setEvent(String event) {
		this.event = event;
	}

	void setGuest(String guest) {
		this.guest = guest;
	}

	void setEst(String est) {
		this.est = est;
	}

	public int compareTo(DataPoint compareData) {

		int compareTime= ((DataPoint) compareData).getTime(); 

		//ascending order
		return this.time - compareTime;

		//descending order
		//return compareQuantity - this.quantity;

	}
	public static Comparator<DataPoint> DataPointComparator 
	= new Comparator<DataPoint>() {
		public int compare(DataPoint data1, DataPoint data2) {
			
			if (field == 1)
			{
				String dataDevice1 = data1.getDevice()+"";
				String dataDevice2 = data2.getDevice()+"";

				int comparison = (dataDevice1).compareTo (dataDevice2);

				if (comparison == 0)
				{
					int otherComp = data1.getId().compareTo(data2.getId());
					if (otherComp == 0)
					{
					 otherComp = data1.getEvent().compareTo(data2.getEvent());
					 return otherComp;
					}
					else {
					return otherComp;
				}}
				else
				{
					return comparison;
				}}
			
			else if (field == 0)
			{
				{
					String dataGuest1 = data1.getGuest()+"";
					String dataGuest2 = data2.getGuest()+"";
					Integer dataTime1 = data1.getTime();
					Integer dataTime2 = data2.getTime();
					int comparison = (dataGuest1).compareTo (dataGuest2);

					if (comparison == 0)
					{
						
						int otherComp = dataTime1.compareTo(dataTime2);
						if (otherComp == 0)
						{
						 otherComp = data1.getId().compareTo(data2.getId());
						 return otherComp;
						}
						else {
						return otherComp;
					}}
					else
					{
						return comparison;
					}}
			}
			else if (field == 3) {
				{
					String dataId1 = data1.getId()+"";
					String dataId2 = data2.getId()+"";
					Integer dataTime1 = data1.getTime();
					Integer dataTime2 = data2.getTime();
					int comparison = (dataId1).compareTo (dataId2);

					if (comparison == 0)
					{
						
						int otherComp = dataTime1.compareTo(dataTime2);
						if (otherComp == 0)
						{
						 otherComp = data1.getDevice().compareTo(data2.getDevice());
						 return otherComp;
						}
						else {
						return otherComp;
					}}
					else
					{
						return comparison;
					}}
			}
			else {
				Integer dataTime1 = data1.getTime();
				Integer dataTime2 = data2.getTime();
				int comparison = dataTime1.compareTo(dataTime2);
				return comparison;
			}

			//ascending order
			//return dataGuest1.toUpperCase().compareTo(dataGuest2.toUpperCase());

			//descending order
			//return dataGuest2.compareTo(dataGuest1);
		}

	};
	
	@Override
	public String toString() {
		/*String output = est + " ";
		
		if(guest.equals("Veronica")) {
			output = output + guest+"  ";
		}
		else if(guest.equals("Jason")) {
			output+=guest+"     ";
		}
		else if(guest.equals("Thomas")) {
			output+=guest+"    ";
		}
		else if(guest.equals("Rob")) {
			output+=guest+"       ";
		}
		else if(guest.equals("Kristina")) {
			output+=guest+"  ";
		}
		else if(guest.equals("Dave")) {
			output+=guest+"      ";
		}
		else if(guest.equals("Salina")) {
			output+=guest+"    ";
		}
		else if(guest.equals("Harrison")) {
			output+=guest+"  ";
		}
		else if(guest.equals("Eugene")) {
			output+=guest+"    ";
		}
		else if(guest.equals("Alok")) {
			output+=guest+"      ";
		}
		*/
		return (String.format("%-25s%-15s%-15s%-15s%-10s", est, guest,device, id,event));
	}
}
