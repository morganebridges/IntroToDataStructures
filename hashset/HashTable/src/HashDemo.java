
public class HashDemo {
	public static void main(String[] args)
	{
		Table2 table = new Table2(16);
		Integer element1 = 15;
		Integer element2 = 16;
		Integer element3 = 17;
		Integer element4 = 18;
		Integer element5 = 19;
		Integer element6 = 32;
		Integer element7 = 33;
		Integer element8 = 34;
		Integer element9 = 17;
		Integer element10 = 18;
		Integer element11 = 19;
		Integer element13= 20;
//		Integer element14= 65;
//		Integer element15= 67;
//		Integer element16= 88;
//		Integer element17= 206;
//		Integer element18= 533;
//		Integer element19= 655;
//		Integer element20= 863;
//		
		System.out.println(table.hash(element6));
		System.out.println(table.hash(element7));
		
		table.put(element1.hashCode(), element1);
		table.put(element2.hashCode(), element2);
		table.put(element3.hashCode(), element3);
		table.put(element4.hashCode(), element4);
		table.put(element5.hashCode(), element5);
		table.put(element6.hashCode(), element6);
		table.put(element7.hashCode(), element7);
		table.put(element8.hashCode(), element8);
		table.put(element9.hashCode(), element9);
		table.put(element10.hashCode(), element10);
		table.put(element11.hashCode(), element11);
		//table.put(table.hash(element12), element12);
		
		table.put(table.hash(element13), element13);
//		table.put(table.hash(element14), element14);
//		table.put(table.hash(element15), element15);
//		table.put(table.hash(element16), element16);
//		table.put(table.hash(element17), element17);
//		table.put(table.hash(element18), element18);
//		table.put(table.hash(element19), element19);
//		table.put(table.hash(element20), element20);
		Integer butt = 432;
		System.out.println(butt.hashCode());
		Double poop = 3.432;
		System.out.println(poop.hashCode());
		System.out.println(table.containsKey(6));
		System.out.println(element6.hashCode());
		table.printData();
	}
	
}
