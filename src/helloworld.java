import java.util.ArrayList;

public class helloworld {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> list = new ArrayList<>();
		appendList(list);
		System.out.println("List 1: " + list.get(0));
	}
	public static void appendList(ArrayList<Integer> list){
		list.add(1);
	}

}
