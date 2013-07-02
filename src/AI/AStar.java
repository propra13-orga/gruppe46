package AI;

import java.util.ArrayList;
import models.*;

public class AStar{
	
	private PlayingField pf;
	private int StartX,StartY,TargetX,TargetY;
	private Field[][] fields;
	private Field target,start;
	private Field current;
	
	private ArrayList<Field> open =new ArrayList<Field>();
	private ArrayList<Field> closed =new ArrayList<Field>();
	private ArrayList<Block> path=new ArrayList<Block>();
	
	public void ClearAll()
	{
		open.clear();
		closed.clear();
		path.clear();
	}
	
	
	/* 
	 * **************************************************************************
	 * *****************************Constructor**********************************
	 * **************************************************************************
	 */
	
	public AStar(PlayingField pf)
	{
		this.pf=pf;
	}

	/* 
	 * **************************************************************************
	 * *****************************Main Algorithm*******************************
	 * **************************************************************************
	 */
	
	/*
	 * Everytime we run findpath, we make sure the lists are empty to prevent
	 * conflicts with a previous path.
	 * Then the main Algorithm starts. More Info: 
	 * http://ru.wikipedia.org/wiki/%D0%90%D0%BB%D0%B3%D0%BE%D1%80%D0%B8%D1%82%D0%BC_%D0%BF%D0%BE%D0%B8%D1%81%D0%BA%D0%B0_A*
	 */
	
	public void findpath()
	{
		open.clear();
		closed.clear();
		path.clear();
		if(!((start.getItem().getPosX()==target.getItem().getPosX())
		   &&(start.getItem().getPosY()==target.getItem().getPosY())))
		{	
		current=start;
		open.add(start);
		while(current!=target)
		{
			current=getCheapestField();
			
		
			if(current==null)
			{
				open.clear();
				closed.clear();
				path.clear();
				current=start;
			}
			try{
			checkAround();
			} catch (Exception e)
			{}
		}
		createPath();
		}
	}
	
	
	
	
	/* 
	 * **************************************************************************
	 * *****************************LIST CHECKERS********************************
	 * **************************************************************************
	 */
	
	/* 
	 * 
	 * Check if a certain Field is already in the OpenList. If so, the method returns true.
	 * 
	 */

	private boolean checkIfOpen(Field f)
	{
		for (int i=0; i<open.size(); i++)
		{
			if ((open.get(i).getArrayPos()[0]==f.getArrayPos()[0]) && (open.get(i).getArrayPos()[1]==f.getArrayPos()[1]))
			{
				return true;
			}
		}
	return false;
	}
	
	/* 
	 * 
	 * Check if a certain Field is already in the ClosedList. If a Field is already in the closed List the field was 
	 * already examined and the method returns true.
	 * 
	 */
	
	private boolean checkIfClosed(Field f)
	{
		for (int i=0; i<closed.size(); i++)
		{
			if (closed.get(i)!=null)
			if ((closed.get(i).getArrayPos()[0]==f.getArrayPos()[0]) && (closed.get(i).getArrayPos()[1]==f.getArrayPos()[1]))
			{
				return true;
			}
		}
	return false;
	}
	
	
	
	
	/* 
	 * **************************************************************************
	 * *******************************Setters************************************
	 * **************************************************************************
	 */
	
	/*
	 * Define the position from where we search the target
	 * create a field called start and add it to the fields 
	 */
	public void setStart(int StartX, int StartY)
	{
		this.StartX=getEvenNumber(StartX);
		this.StartY=getEvenNumber(StartY);
		start=new Field(pf.getFieldarray()[this.StartX][this.StartY],0,0);
		fields[this.StartX][this.StartY]=start;
		start.setArrayPos(this.StartX, this.StartY);
	}
	
	/*
	 * Define the position from where we search the target
	 * create a field called start and add it to the fields 
	 */
	
	public void setTarget(int TargetX, int TargetY)
	{
		this.TargetX=getEvenNumber(TargetX);
		this.TargetY=getEvenNumber(TargetY);
		target=new Field(pf.getFieldarray()[this.TargetX][this.TargetY],0,0);
		fields[this.TargetX][this.TargetY]=target;
		target.setArrayPos(this.TargetX, this.TargetY);
	}
	
	
	/* 
	 * **************************************************************************
	 * *******************************Creators***********************************
	 * **************************************************************************
	 */
	
	private void createPath()
	{
		while(!(current==start))
		{
			path.add(current.getItem());
			current=current.getParent();
		}
		path.add(start.getItem());
			
	}
	
	
	public void createFieldarray()
	{
		fields=new Field[pf.getFieldarray().length][pf.getFieldarray()[0].length];
		for (int i=0; i<pf.getFieldarray().length; i++)
		{
			for (int j=0; j<pf.getFieldarray()[0].length; j++)
			{
			  Field newField=new Field(pf.getFieldarray()[i][j],0,0);
			  newField.setArrayPos(i, j);
			  fields[i][j]=newField;
			  
			}
		}
	}
	
	/* 
	 * **************************************************************************
	 * *****************************Calculators**********************************
	 * **************************************************************************
	 */
	
	/*
	 *  checkAround() checks the fields around the current field on following 
	 *  conditions:
	 *  (1) is there a field? (if not aroundCurrent[i]==null)
	 *  (2) is the current field already in the open list?
	 *  (3) is the current field already in the closed list?
	 *  (4) is the current field movable?
	 *  
	 */
	
	private void checkAround()
	{
		Field[] aroundCurrent=GetAroundCurrent();
		for (int i=0; i<4; i++)
		{
			 
			if 	 ( (!(aroundCurrent[i]==null))
				&& (!(checkIfOpen(aroundCurrent[i]))) 
				&& (!(checkIfClosed(aroundCurrent[i])))
				&& (aroundCurrent[i].isMovable()) )
				{
					aroundCurrent[i].setG(10);
					aroundCurrent[i].setH(calcHeuristic(aroundCurrent[i]));
					aroundCurrent[i].setParent(current);
					open.add(aroundCurrent[i]);	
				}
		}
		closed.add(current);
		open.remove(current);

	}
	
     
	/*
	 * Following Method checks the fields around the current field and writes
	 * them into an Array (aroundCurrent) this array will be  returned in the end
	 * if the field does not have any other field above (e.g. (0,0)) or at its
	 * left hand side the Array got the entry 'null' at this position.
	 * Definiton:
	 * aroundCurrent[0] -> above
	 * aroundCurrent[1] -> left
	 * aroundCurrent[2] -> below
	 * aroundCurrent[3] -> right
	 */
	private Field[] GetAroundCurrent()
	{
		Field[] aroundCurrent= new Field[4];
		for(int i=0; i<4; i++)
			aroundCurrent[i]=null;
		
		int x=current.getArrayPos()[0];
		int y=current.getArrayPos()[1];
		
		
		if ((current.getArrayPos()[0]-1)>=0)
		{
			aroundCurrent[0]=fields[current.getArrayPos()[0]-1][current.getArrayPos()[1]];
		}
		
		if ((current.getArrayPos()[1]-1)>=0)
		{
			aroundCurrent[1]=fields[current.getArrayPos()[0]][current.getArrayPos()[1]-1];
		}
				
		if ((current.getArrayPos()[0]+1)<fields.length)
		{
			aroundCurrent[2]=fields[current.getArrayPos()[0]+1][current.getArrayPos()[1]];
		}
					
		if ((current.getArrayPos()[1]+1)<fields[0].length)
		{
			aroundCurrent[3]=fields[current.getArrayPos()[0]][current.getArrayPos()[1]+1];
		}
	
		return aroundCurrent;			
	}
	
	
	/*
	 * getChepestField() calculates the cheapest field in the open list
	 * and returns it.
	 */
	
	private Field getCheapestField()
	{
		
		int costs=999999;
		Field cheapest=null;
		for (int i=0; i<open.size(); i++)
		{	
			if(open.get(i).getf()<costs)
			{
				cheapest=open.get(i);
				costs=open.get(i).getf();
			}
		
		}
		return cheapest;
	
	}
	
   /* 
    * We chose the Euclidian heuristic for our algorithm, because it delivers the best results.
    * This method calculates the Euclidian heuristic.
    */

	public int calcHeuristic(Field field)
	{
		return (int) ( (Math.pow((field.getArrayPos()[0]-TargetX),2)+Math.pow((field.getArrayPos()[1]-TargetY),2)));
	}
	
	/* 
	 * **************************************************************************
	 * *****************************Helpers**************************************
	 * **************************************************************************
	 */
	
	/* 
	 * calculates the index of a field 
	 */
	private int getEvenNumber(int n)
	{
		return ((n-(n%60))/60);
	}

	/* 
	 * **************************************************************************
	 * *****************************Getters**************************************
	 * **************************************************************************
	 */
	
	/*
	 * returns the final path.
	 */
	public ArrayList<Block> getPath()
	{
	return path;	
	}
}
