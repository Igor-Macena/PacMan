/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Igor
 */
public class Ghost extends PieceMove{
    public boolean killer = false;
    Pac pac = new Pac();
    public boolean ghostx = false, ghosty = false;
    public long timeDie;
    public boolean die = false;
    public int sprite = 2;
    private int a = 1;
    public Ghost(){
        height = 25;
        width = 25;
        velx = 0;
        vely = 0;
        x = 385;
        y = 185;
    }
    
    public void kill(){
        if (pac.timeBoost > 0)
        this.killer = true;    
        
        else
        this.killer = false;
    }
    
    @Override
    public void draw(Graphics g) {
        if ((System.currentTimeMillis() - timeDie)> 5000) { // tempo da morte do Ghost
            timeDie = 0;
        }
        // definindo formato de Ghost
        g.setColor(this.color);
        g.fillOval(x, y, width, height);
        g.fillRect(x, y+(height/2), width, height/2);
        //Contorno do Olho
        g.setColor(new Color(0,0,0));
        g.fillArc(x+((width/2)+width/17), y+((width/6)+width/20), height/2-height/10, height/2-height/10, 215, 205);
        //Contorno do Olho 2
        g.fillArc(x+(width/7), y+((width/6)+width/20), height/2-height/10, height/2-height/10, 130, 200);
        //Olho
        g.setColor(new Color(255,255,255));
        g.fillArc(x+((width/2)+width/15), y+((width/6)+width/15), height/3, height/3, 215, 205);
        //Olho 2
        g.fillArc(x+(width/6), y+((width/6)+width/15), height/3, height/3, 130, 200);
        
        
        if (sprite == 1) { // sprite nº2
        // perninhas do fantasma ou seja lá o que for aquilo embaixo dele
        g.setColor(this.color);
        g.fillOval(x, y+(((width/2)+width/8)+width/6), ((width*2)/5)-width/10, ((width*2)/5)-width/10);
        
        g.setColor(this.color);
        g.fillOval((x+width/3)-width/width, y+(((width/2)+width/8)+width/6), ((width*2)/5)-width/10, ((width*2)/5)-width/10);
        
        g.setColor(this.color);
        g.fillOval((x+(width/3)*2)-width/width, y+(((width/2)+width/8)+width/6), ((width*2)/5)-width/10, ((width*2)/5)-width/10);
        }
        
        else if (sprite == 2) { // sprite nº2
            // perninhas do fantasma ou seja lá o que for aquilo embaixo dele
        g.setColor(this.color);
        g.fillOval(x + width*2/width, y+(((width/2)+width/8)+width/6), ((width*2)/5)-width/10, ((width*2)/5)-width/10);
        
        g.setColor(this.color);
        g.fillOval(((x+width/3)-width/width)+ width*2/width, y+(((width/2)+width/8)+width/6), ((width*2)/5)-width/10, ((width*2)/5)-width/10);
        
        g.setColor(this.color);
        g.fillOval((x+(width/3)*2)+ width/width, y+(((width/2)+width/8)+width/6), ((width*2)/5)-width/10, ((width*2)/5)-width/10);
        }

        this.a++;
        if (a > 1) {
            switch(sprite){
            case 1:
            sprite = 2;
                break;
            case 2:
            sprite = 1;
                break;
            }
        a = 0;
        } 
    }
    
    public Collide collideG(Block b){ // Método de colisão do Ghost    Ps: Mantive por valor sentimental...
        Collide c = new Collide();
        
        for (int i = 0; i < 2; i++) { // For da colisão do ghost e do block
           int p1x, p1y, p2x, p2y, p3x, p3y, p4x, p4y;
           int b1x, b1y, b2x, b2y, b3x, b3y, b4x, b4y;
            if (i == 0) { // Colisão do ghost em Relação aos Blocks
        // Pontos de colisão do ghost
        p1x = this.x;// Canto superior esquerdo
        p1y = this.y;
        
        p2x = this.x + this.width;// Canto superior direito
        p2y = this.y;
        
        p3x = this.x + this.width;// Canto inferior direito
        p3y = this.y + this.height;
        
        p4x = this.x; // Canto inferior esquerdo
        p4y = this.y + this.height;
        
        // Pontos de colisão do(s) bloco(s)
        b1x = b.x;// Canto superior esquerdo
        b1y = b.y;
        
        b2x = b.x + b.width; // canto superior direito
        b2y = b.y; 
        
        b3x = b.x + b.width;// Canto inferior direito
        b3y = b.y + b.height;
       
        b4x = b.x;// Canto inferior esquerdo
        b4y = b.y + b.height;
        
        // Calculo de distancia entre os blocos
        int d1x = p1x - b2x; // Distancia (X) do P1x até o B2x
        int d1y = p1y - b4y; // e etc... \_(º-º)_/
        int d2x = b1x - p2x;
        int d2y = p2y - b3y;
        int d3x = b4x - p3x;
        int d3y = b2y - p3y;
        int d4x = p4x - b3x;
        int d4y = b2y - p4y;
        
        // ghost ponto 1 (X,Y) Canto superior esquerdo
        if (p1x >= b1x && p1x <= b2x) {
            if (p1y >= b1y && p1y <= b4y) {
                if (velx == -5) {   // Ponto1 X
                this.x = x - d1x + 5;
                this.velx = 0;
                this.ghostx = true;
                }
                 if (vely == -5) {  // Ponto1 Y
                this.y = y - d1y + 5;
                this.vely = 0;
                this.ghosty = true;
                }
            }
        }
        
        // ghost ponto 2 (X,Y) Canto superior direito
        if (p2x >= b1x && p2x <= b2x) {
            if (p2y >= b1y && p2y <= b4y) {
                if (velx == 5) {    // Ponto2 X
                this.x = x + d2x - 5;
                this.velx = 0;
                this.ghostx = true;
                }
                 if (vely == -5) {  // Ponto2 Y
                this.y = y - d2y + 5;
                this.vely = 0;
                this.ghosty = true;
                }
            }
        }
        // ghost ponto 3 (X,Y) Canto inferior direito
         if (p3x >= b1x && p3x <= b2x) {
            if (p3y >= b1y && p3y <= b4y) {
                if (velx == 5) {    // Ponto3 X
                this.x = x + d3x - 5;
                this.velx = 0;
                this.ghostx = true;
                }
                 if (vely == 5) {   // Ponto3 Y
                this.y = y + d3y - 5;
                this.vely = 0;
                this.ghosty = true;
                }
            }
        }
         // ghost ponto 4 (X,Y) Canto inferior esquerdo
         if (p4x >= b1x && p4x <= b2x) {
            if (p4y >= b1y && p4y <= b4y) {
                if (velx == -5) {   // Ponto4 X
                this.x = x - d4x + 5;
                this.velx = 0;
                this.ghostx = true;
                }
                 if (vely == 5) {   // Ponto4 Y
                this.y = y + d4y - 5;
                this.vely = 0;
                this.ghosty = true;
                }
            }
        }
    }
            else{ // Colisão dos Blocks em relção ao ghost
            
        b1x = this.x;// Canto superior esquerdo
        b1y = this.y;
        
        b2x = this.x + this.width;// Canto superior direito
        b2y = this.y;
        
        b3x = this.x + this.width;// Canto inferior direito
        b3y = this.y + this.height;
        
        b4x = this.x; // Canto inferior esquerdo
        b4y = this.y + this.height;
        
        // Pontos de colisão do(s) bloco(s)
        p1x = b.x;// Canto superior esquerdo
        p1y = b.y;
        
        p2x = b.x + b.width; // canto superior direito
        p2y = b.y; 
        
        p3x = b.x + b.width;// Canto inferior direito
        p3y = b.y + b.height;
       
        p4x = b.x;// Canto inferior esquerdo
        p4y = b.y + b.height;
        
        // Calculo de distancia entre os blocos
        int d1x = b2x - p1x; // Distancia (X) do P1x até o B2x
        int d1y = b4y - p1y; // e etc... \_(º-º)_/
        int d2x = p2x - b1x;
        int d2y = b3y - p2y;
        int d3x = p3x - b4x;
        int d3y = p3y - b2y;
        int d4x = b3x - p4x;
        int d4y = p4y - b2y;
        
        // Block ponto 1 (X,Y) Canto superior esquerdo
        if (p1x >= b1x && p1x <= b2x) {
            if (p1y >= b1y && p1y <= b4y) {
                if (velx == 5) {   // Ponto1 X
                this.x = x - d1x + 5;
                this.velx = 0;
                this.ghostx = true;
                }
                 if (vely == 5) {  // Ponto1 Y
                this.y = y - d1y + 5;
                this.vely = 0;
                this.ghosty = true;
                }
                 return c;
            }
        }
        // Block ponto 2 (X,Y) Canto superior direito
        if (p2x >= b1x && p2x <= b2x) {
            if (p2y >= b1y && p2y <= b4y) {
                if (velx == -5) {    // Ponto2 X
                this.x = x + d2x - 5;
                this.velx = 0;
                this.ghostx = true;
                }
                 if (vely == 5) {  // Ponto2 Y
                this.y = y - d2y + 5;
                this.vely = 0;
                this.ghosty = true;
                }
                 return c;
            }
        }
        // Block ponto 3 (X,Y) Canto inferior direito
         if (p3x >= b1x && p3x <= b2x) {
            if (p3y >= b1y && p3y <= b4y) {
                if (velx == -5) {    // Ponto3 X
                this.x = x + d3x - 5;
                this.velx = 0;
                this.ghostx = true;
                }
                 if (vely == -5) {   // Ponto3 Y
                this.y = y + d3y - 5;
                this.vely = 0;
                this.ghosty = true;
                }
                 return c;
            }
        }
         // Block ponto 4 (X,Y) Canto inferior esquerdo
         if (p4x >= b1x && p4x <= b2x) {
            if (p4y >= b1y && p4y <= b4y) {
                if (velx == 5) {   // Ponto4 X
                this.x = x - d4x + 5;
                this.velx = 0;
                this.ghostx = true;
                }
                 if (vely == -5) {   // Ponto4 Y
                this.y = y + d4y - 5;
                this.vely = 0;
                this.ghosty = true;
                }
                 return c;
                }
            }
        }
    }
    return c; // Retornando os valores
    }
    
    public void move(boolean movement){
        if (movement == true) {
        int g = (int) +(Math.random()*4)+1;// sorteando a direção do Ghost
        switch(g){ // Switch case é quase um if else porem se limita a uma variavel
            case 1:// esquerda
                if (velx == 0 && vely == 0)  // Quando um if só ocupa um linha
                this.velx = 5;
                break;
            case 2:// baixo
                if(vely == 0 && velx == 0)
                this.vely = 5;
                break;
            case 3:// direita
                if (velx == 0 && vely == 0) 
                this.velx = -5;
                break;
            case 4:// cima
                if(vely == 0 && velx == 0)
                this.vely = -5;
                break;
            default:
                System.out.println("Não quero andar (-_-)");
        }
        if (this.velx != 0 || this.vely != 0) {
        this.x = this.x + this.velx;
        this.y = this.y + this.vely;
            }
        }
        if (timeDie != 0) {
            this.x = 385;
            this.y = 185;
            this.velx = 0;
            this.vely = 0;
            die = true;
        }
               
    }
    public void weakGhost(){
        timeDie = System.currentTimeMillis(); // Contador do ghost morto
    }
}