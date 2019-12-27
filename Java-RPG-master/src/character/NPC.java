package character;

import game.Game;

public abstract class NPC {
	public int ID;
	protected String[] msg;
	protected int talkctr;
	public static boolean isTalking;
	public NPC(int ID,String[] m,int talkctr) {
		this.ID=ID;
		msg=m;
		this.talkctr=talkctr;
	}
	
	public  void talkTo() {
		
		if(msg[talkctr].equals("ENDOFLINE"))
		{
			talkctr = 0;
			Game.dialog.hideDialog();
			isTalking=false;
		}
		else
		{
			isTalking = true;
			Game.dialog.showDialog(msg[talkctr]);
			talkctr++;
		}
	};
		
	
}
