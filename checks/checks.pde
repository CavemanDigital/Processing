  int col = 10;
  int row = 10;
  int colwidth =  400/col;
  int rowheight =  400/row; 
  int players=2;
  int n=0;
  int[] player=new int[players];
  int[][] istaken = new int[col][row];
  boolean[][] checked = new boolean[col][row]; 
  

void  setup(){
  size(400,400);
  smooth();
  stroke(0);
  for (int c=0; c<player.length; c++){
    player[c]=(255/players)*c; 
  }
  for(int g=0; g<col; g++) { for(int b=0; b< row; b++) { istaken[g][b]=5; }}
  for (int i = 0; i < 400; i+=colwidth){line (i, 0, i, 400);}
  for (int i = 0; i < 400; i+=rowheight){line (0, i, 400, i);}
  colorMode(HSB);
  ellipseMode(CENTER);
}

void draw(){}
void mousePressed(){ 
     for(int g=0; g<col; g++) { for(int b=0; b< row; b++) { checked[g][b]=false; }}
     int theCol=mouseX/colwidth;
     int theRow=mouseY/rowheight;
     int piece=player[n];
     fill(player[n],255,255);
     check(theCol,theRow,piece);
     n=(n+1)%players;
}

void check(int theCol, int theRow, int currentPiece){
    try{  
     if((istaken[theCol][theRow]!=5)&&(istaken[theCol][theRow]==currentPiece)){
       ellipse((colwidth*(theCol+1))+(.5*colwidth), (rowheight*theRow)+(.5*rowheight), colwidth*.80, rowheight*.80);
       istaken[theCol+1][theRow]=currentPiece;
       ellipse((colwidth*(theCol-1))+(.5*colwidth), (rowheight*theRow)+(.5*rowheight), colwidth*.80, rowheight*.80);
       istaken[theCol-1][theRow]=currentPiece;
       ellipse((colwidth*theCol)+(.5*colwidth), (rowheight*(theRow+1))+(.5*rowheight), colwidth*.80, rowheight*.80);
       istaken[theCol][theRow+1]=currentPiece;
       ellipse((colwidth*theCol)+(.5*colwidth), (rowheight*(theRow-1))+(.5*rowheight), colwidth*.80, rowheight*.80);
       istaken[theCol][theRow-1]=currentPiece;
     }
     if((istaken[theCol][theRow]!=5)&&(istaken[theCol][theRow]!=currentPiece)){
       n--;
     }
     if(istaken[theCol][theRow]==5){
       ellipse((colwidth*theCol)+(.5*colwidth), (rowheight*theRow)+(.5*rowheight), colwidth*.80, rowheight*.80);
       istaken[theCol][theRow]=currentPiece;
     }
    }catch (Exception e) {}
}










