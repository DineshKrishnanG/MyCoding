package MyCoding;

import java.util.HashMap;
import java.util.Map;
/*
 * There are 100 empty slots.
 * J1 to J5 are jumpers placed at position 2-6 respectively
 * J is one more jumper placed at position 1.
 * J1 to J5 can Jump 16 10 5 3 1 places respectively
 * Find: The jump of J so that it will not collide with any other Jumper.
 * 	# If J1-J5 jumper collide with each other they are out of the game
 *  # If Jump crosses the boundary 1-100 it will return in reverse order
 *  	Example: a) if J1 is 98 , as it can jump 16 places, the new position wil be 86 and the flow is in reverse order.
 *  			b) And if J1 is in 2 slot(reverse), the new position will be 14. 
 *  			c) All the jumpers jump 100 times and J Jumper must of collide in any of the jumps.
 *  
 */
public class Jumpers {
	private static final int LOWER_RANGE = 0;
	private static final int UPPER_RANGE = 100;
	private static final int FORWARD = 1;
	private static final int REVERSE = -1;
	public static void main(String[] args) {

		OUTER:
		for(int x=1;x<=100;x++){
			Jumper j1 = new Jumper(1,2,FORWARD,16);
			Jumper j2 = new Jumper(2,3,FORWARD,10);
			Jumper j3 = new Jumper(3,4,FORWARD,5);
			Jumper j4 = new Jumper(4,5,FORWARD,3);
			Jumper j5 = new Jumper(5,6,FORWARD,1);
			Jumper j6 = new Jumper(6,1,FORWARD,2);	//J Jumper
			
			Jumper[] jumpers = new Jumper[]{j1,j2,j3,j4,j5,j6};
			/*System.out.println("ITR \t J_"+j1.id+ " \t J_"+j2.id+" \t J_"+j3.id+""
					+ "\t J_"+j4.id+"\t J_"+j5.id);
			System.out.println("[0] \t ["+j1.position+ "] \t["+j2.position+"]\t ["+j3.position+"]"
					+ "\t["+j4.position+"] \t["+j5.position+"] \tJ["+j6.position+"]");*/
			j6.jump_length = x;//TRIAL RUN- BRUTE FORCE
			
			INNER:
			for(int i=1; i<1000; i++){
				//System.out.print("\n ["+i+"] ");
				Map<Integer,Integer> curpos = new HashMap<Integer,Integer>();
				LOCAL:
				for( int j=0;j<jumpers.length;j++){
					Jumper jumpObj = jumpers[j];
					if( jumpObj.isActive){
						int next = jumpObj.position + (jumpObj.jump_length * jumpObj.direction);
						if(next > UPPER_RANGE){
							next -= 2*(next-UPPER_RANGE);
							jumpObj.direction = REVERSE;
						}else if(next < LOWER_RANGE){
							next =  LOWER_RANGE - next;
							jumpObj.direction = FORWARD;
						}
						jumpObj.position=next;
						//System.out.print("\t["+jumpObj.position+"]");
						if(curpos.containsKey(jumpObj.position)){
							jumpObj.isActive=false;
							int id = curpos.get(jumpObj.position);
							jumpers[id-1].isActive = false;
						}else{
							curpos.put(jumpObj.position,jumpObj.id);
						}
					}else{
						
						//System.out.print("\t:[X]");
						if(jumpObj.id == 6 ){
							//System.out.println("Successful["+j6.isActive+"] value ["+j6.jump_length+"]");
							continue OUTER;
						}
					}
				}
			}
			System.out.println("Successful ["+j6.isActive+"] Hopcount ["+j6.jump_length+"]");
		}
	}
}

class Jumper{
	int id;
	int direction;
	int jump_length;
	boolean isActive=true;
	int position;
	 
	public Jumper(int id, int position, int direction, int jump_length){
		this.id = id;
		this.position = position;
		this.direction = direction;
		this.jump_length = jump_length;
	}
}
