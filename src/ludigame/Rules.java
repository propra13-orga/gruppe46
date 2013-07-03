/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ludigame;

public class Rules {

    private Spieler sp;
    private PlayingField pf;
    private ViewPlayer vp;
    private ViewPlayingField vpf;
    private int speed = 5;
    private int block = 60;
    private int spieler =40;

    public Rules(Spieler sp, ViewPlayer vp, PlayingField pf, ViewPlayingField vpf) {
        this.pf = pf;
        this.sp = sp;
        this.vp = vp;
        this.vpf=vpf;
     
        

    }


    private String getHorizType(int posX, int posY) {

        String item = "";
        for (int i = 0; i < pf.getFieldarray().length; i++) {
            if ((pf.getFieldarray()[i].getPosX() == posX) 
            		&& (pf.getFieldarray()[i].getPosY() < posY + 40) 
            		&& (posY < pf.getFieldarray()[i].getPosY() + 40)) {
                item = pf.getFieldarray()[i].getType();
                 if(item.equals("bonus")){
                     
                  vpf.getViewItems()[i].setImage("images/boden.png");
                  pf.getFieldarray()[i].setType("nix");
        }
            }


        }
       
        return item;
    }

    private String getVertItemType(int posX, int posY) {
        String item = "";
        for (int i = 0; i < pf.getFieldarray().length; i++) {
            if ((pf.getFieldarray()[i].getPosX() + spieler > posX) 
            		&& (pf.getFieldarray()[i].getPosX() < posX + spieler) 
            		&& (posY == pf.getFieldarray()[i].getPosY())) {
        
                item = pf.getFieldarray()[i].getType();
                if(item.equals("bonus")){
                    
                  vpf.getViewItems()[i].setImage("images/boden.png");
                  pf.getFieldarray()[i].setType("nix");
        }
            }


        }
        return item;
    }

    public void pMoveLE() {
        vp.setImage("images/playerL.png");
        String temp = getHorizType(sp.getPosX() - block, sp.getPosY());
        if (sp.getPosX() - speed >= 0) {
            switch (temp) {
                case "wall":
                    System.out.println("Wand");
                    break;
                case "bonus":
               
                    sp.setPos(sp.getPosX() - speed, sp.getPosY());
                    System.out.println("Bonus");
                	
                    break;
                case "":
                	 sp.setPos(sp.getPosX() - speed, sp.getPosY());
                    break;
                case "enemy":
                    sp.setPos(sp.getPosX() - speed, sp.getPosY());
                    sp.killplayer();
                    System.out.println("Ende im Gelände");
                    break;
                case "finish":
                    sp.setPos(sp.getPosX() - speed, sp.getPosY());
                    pf.increaseLayer();
                    case"nix":
                        sp.setPos(sp.getPosX() - speed, sp.getPosY());
            }
        }


    }

    public void pMoveRE() {
        vp.setImage("images/playerR.png");
        if (sp.getPosX() + speed < pf.getWidth()-spieler) {
            String temp = getHorizType(sp.getPosX() + 40, sp.getPosY());

            switch (temp) {
                case "wall":
                    System.out.println("Wand");
                    break;
                case "bonus":
                    sp.setPos(sp.getPosX() + speed, sp.getPosY());
                    System.out.println("Bonus");
                    break;

                case "enemy":
                    sp.setPos(sp.getPosX() + speed, sp.getPosY());
                    sp.killplayer();
                    System.out.println("Ende im Gelände");
                    break;
                case "":
                    sp.setPos(sp.getPosX() + speed, sp.getPosY());
                    break;
                case "finish":
                    sp.setPos(sp.getPosX() + speed, sp.getPosY());
                    pf.increaseLayer();
                     case"nix":
                        sp.setPos(sp.getPosX() + speed, sp.getPosY());

            }


            temp = "";
        }

    }

    public void pMoveUP() {
        vp.setImage("images/playerB.png");

        if (sp.getPosY() - speed >= 0) {
            String temp = getVertItemType(sp.getPosX(), sp.getPosY() - block);
            switch (temp) {
                case "wall":
                    System.out.println("Wand");
                    break;
                case "bonus":
                    sp.setPos(sp.getPosX(), sp.getPosY() - speed);
                    System.out.println("Bonus");
                    break;
                case "enemy":
                    sp.setPos(sp.getPosX(), sp.getPosY() - speed);
                    sp.killplayer();
                    System.out.print("Ende im Gelände");
                    break;
                case "":
                    sp.setPos(sp.getPosX(), sp.getPosY() - speed);
                    break;
                case "finish":
                    sp.setPos(sp.getPosX(), sp.getPosY() - speed);
                    pf.increaseLayer();
                     case"nix":
                        sp.setPos(sp.getPosX(), sp.getPosY()-speed);
            }
        }
    }

    public void pMoveDO() {
        vp.setImage("images/playerV.png");
        if (sp.getPosY() + speed < pf.getHeight()-spieler*2) {
            String temp = getVertItemType(sp.getPosX(), sp.getPosY() + spieler);

            switch (temp) {
                case "wall":
                    System.out.println("Wand");
                    break;
                case "bonus":
                    System.out.println("Bonus");
                    sp.setPos(sp.getPosX(), sp.getPosY() + speed);
                    break;
                case "enemy":
                    sp.setPos(sp.getPosX(), sp.getPosY() + speed);
                    sp.killplayer();
                    System.out.print("Ende im Gelände");
                    break;
                case "":
                    sp.setPos(sp.getPosX(), sp.getPosY() + speed);
                    break;
                case "finish":
                    sp.setPos(sp.getPosX(), sp.getPosY() + speed);
                    pf.increaseLayer();
                     case"nix":
                        sp.setPos(sp.getPosX() , sp.getPosY()+speed);
            }

        }
    }
 

    
}