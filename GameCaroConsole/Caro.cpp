#include <iostream>
#include <conio.h>
#include <stdio.h>
#include <stdlib.h>
#include <windows.h>
#include <time.h>
#include <unistd.h>
//----------------------------------------------------------------------------------------
/*
+75: phím sang trai
+77: phím sang ph?i
+72: phím lên trên
+80: phím xu?ng
+27: phim ESC  */
void clrscr()
{
	CONSOLE_SCREEN_BUFFER_INFO	csbiInfo;                  
	HANDLE	hConsoleOut;
	COORD	Home = {0,0};
	DWORD	dummy;

	hConsoleOut = GetStdHandle(STD_OUTPUT_HANDLE);
	GetConsoleScreenBufferInfo(hConsoleOut,&csbiInfo);

	FillConsoleOutputCharacter(hConsoleOut,' ',csbiInfo.dwSize.X * csbiInfo.dwSize.Y,Home,&dummy);
	csbiInfo.dwCursorPosition.X = 0;
	csbiInfo.dwCursorPosition.Y = 0;
	SetConsoleCursorPosition(hConsoleOut,csbiInfo.dwCursorPosition);
}
//screen: goto [x,y]
void gotoXY (int column, int row)
{
	COORD coord;
	coord.X = column;
	coord.Y = row;
	SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE),coord);
}

void TextColor (int color)
{
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE) , color);
}
//========================================================================================
struct ToaDo{
	int x;
	int y;
	int data[42][24];
	int number;
};

void NewGame(int row,int column,ToaDo &M){     //ve ra giao dien man hinh`
	//----------------khoi tao gia tri-------------
	M.x=21;
	M.y=13;
	M.number=1;
	for(int i=1;i<42;i++){
		for(int j=1;j<24;j++){
			M.data[i][j]=0;
		}}
	//------------------------------------------
	system("cls");
	system("color 1a");
	row=row*2;
	column=column*2;
	//Khoi tao o luoi
	for(int j=0;j<=row;j++){
		for(int i=0;i<=column;i++){
			if(j%2==0){
				if(i%2==0)
					putchar(197);
				else 
					putchar(196);
			}
			else{
				if(i%2==0)
					putchar(179);
				else
					printf(" ");	
			}
		}
	printf("\n");
	}
	//khoi tao thong tin 
	for(int i=0;i<=30;i++){
		gotoXY(43,i);
		printf("%c",176);
		}
	for(int i=9;i<=20;i++){
		gotoXY(60,i);
		printf("%c",176);
	}
	//in ra huong dan player 2
	gotoXY(66,11);  printf("Player 2:  0");
	gotoXY(62,15);  printf("Dieu khien");
	gotoXY(66,17); putchar(24);
	gotoXY(66,18); putchar(25);
	gotoXY(68,18); putchar(26);	
	gotoXY(64,18); putchar(27);	
	gotoXY(70,18); printf("    Enter");
	//=============in ra thong tin ============================	
	gotoXY(45,1);  printf("F2  : New Game"); 
	gotoXY(45,3);  printf("ESC : Exit game");	
	gotoXY(45,9);  printf("By: Nguyen Tuan Vu");
	gotoXY(44,20);  printf("************************************"); 
	gotoXY(44,10);  printf("************************************"); 
	//----------------in ra Player 1
	TextColor(12); 
	gotoXY(46,11); printf("Player 1:  X"); 
	gotoXY(44,15); printf("Dieu khien");
	gotoXY(46,18); printf("A S D   SPACE");
	gotoXY(48,17); printf("W");	
	//con tro ra giua man hinh
	gotoXY(21,13);
}
void Process(ToaDo &M){
	{
			if(M.number%2)
				{															
					gotoXY(M.x,M.y);				
					printf("X");
					M.data[M.x][M.y]=1;	
					
					M.number=2;
					TextColor(10);	 						
				}	
			else
				{																							
					gotoXY(M.x,M.y);
					printf("0");
					M.data[M.x][M.y]=2;
					
					M.number=1;
					TextColor(12);
				}		
		}
		gotoXY(M.x,M.y);
}
int CheckToaDo(ToaDo &M){	
	if(M.data[M.x][M.y]==1||M.data[M.x][M.y]==2)
		return 0;
	else return 1;
}
/*
+75: phím sang trai
+77: phím sang ph?i
+72: phím lên trên
+80: phím xu?ng
+27: phim ESC  */
void Control_P2(ToaDo &M){
	{
		char key;
		key=_getch();
		switch(key){
			case 75: if(M.x>1) M.x=M.x-2; break; //sang trai
			case 77: if(M.x<41) M.x=M.x+2; break;		//sang phai
			case 72: if(M.y>1) M.y=M.y-2; break;		//len
			case 80: if(M.y<29) M.y=M.y+2; break;		//xuong
			case 13: if(CheckToaDo(M)) Process(M); break;  //enter 
			case '<': NewGame(15,21,M); break;				//phim F2
			case 27: exit(0);
		}
		gotoXY(73,24);	printf("(%-2d;%-2d)",M.x,M.y);
		gotoXY(45,21);	printf("Den luot Player %d",M.number);
		gotoXY(M.x,M.y);
	}
}
void Control_P1(ToaDo &M){
	{
		char key;
		key=_getch();
		switch(key){
			case 'a': if(M.x>1) M.x=M.x-2; break; //sang trai
			case 'd': if(M.x<41) M.x=M.x+2; break;		//sang phai
			case 'w': if(M.y>1) M.y=M.y-2; break;		//len
			case 's': if(M.y<29) M.y=M.y+2; break;		//xuong
			case  32: if(CheckToaDo(M)) Process(M); break;
			case  '<': NewGame(15,21,M); break;            //phim F2 newgame
			case  27: exit(0);
		}
		gotoXY(73,24);	printf("(%-2d;%-2d)",M.x,M.y);
		gotoXY(45,21);	printf("Den luot Player %d",M.number);
		gotoXY(M.x,M.y);
	}
}



int CheckEndGame(ToaDo &M){
		//check o NULL
		if(M.data[M.x][M.y]==0) return 0;
		// check rows
		int x=M.x-2;
		int countx=1, county=1;
        while(x>=0 && M.data[x][M.y]==M.data[M.x][M.y])
	        {
	            countx ++;
	            x=x-2;
	        }
    	x=M.x+2;
       	while(x<=41 && M.data[x][M.y]==M.data[M.x][M.y])
	        {
	            countx ++;
	            x=x+2;
	        }
	    //check column    
	    int y=M.y-2;
        while(y>=0 && M.data[M.x][y]==M.data[M.x][M.y])
	        {
	            county ++;
	            y=y-2;
	        }
    	y=M.y+2;
       	while(y<=29 && M.data[M.x][y]==M.data[M.x][M.y])
	        {
	            county ++;
	            y=y+2;
	        }
		//check duong cheo chinh 
		int countc=1;
		x=M.x-2, y=M.y-2;
		while(x>=0 && M.data[x][y]==M.data[M.x][M.y])
			{
				countc++;
				x=x-2;
				y=y-2;			
			}
		x=M.x+2, y=M.y+2;	
		while(y<= 29 && M.data[x][y]==M.data[M.x][M.y])
			{
				countc++;
				x=x+2;
				y=y+2;			
			}		
		// check duong cheo phu	
		int countp=1;
		x=M.x-2, y=M.y+2;
		while(x>=0 && M.data[x][y]==M.data[M.x][M.y])
			{
				countp++;
				x=x-2;
				y=y+2;			
			}
		x=M.x+2, y=M.y-2;	
		while(y<= 29 && M.data[x][y]==M.data[M.x][M.y])
			{
				countp++;
				x=x+2;
				y=y-2;			
			}		
		if(countx >=5||county>=5||countc>=5|| countp>=5) return 1;
		else return 0;				
}

void EndGame(ToaDo &M){
	char key;
	do{
	key=getch();
	switch(key){
		case '<': NewGame(15,21,M); break;
		case 27: exit(0);
	}
	}while(key!=32);
}

void NameGame(){
	//chon color
	srand(time(NULL));// thay doi theo thoi gian
	//in ra chu C
	TextColor(rand()%7+8); gotoXY(22,6); putchar(2);
	TextColor(rand()%7+8); gotoXY(22,7); putchar(2);
	TextColor(rand()%7+8); gotoXY(22,8); putchar(2);
	TextColor(rand()%7+8); gotoXY(22,9); putchar(2);
	TextColor(rand()%7+8); gotoXY(24,5); putchar(2);
	TextColor(rand()%7+8); gotoXY(24,10); putchar(2);
	TextColor(rand()%7+8); gotoXY(26,5); putchar(2);
	TextColor(rand()%7+8); gotoXY(26,10); putchar(2);
	//in ra chu A
	TextColor(rand()%7+8); gotoXY(28,10); putchar(2);
	TextColor(rand()%7+8); gotoXY(29,9); putchar(2);
	TextColor(rand()%7+8); gotoXY(30,8); putchar(2);putchar(2);putchar(2);putchar(2);putchar(2);putchar(2);putchar(2);
	TextColor(rand()%7+8); gotoXY(31,7); putchar(2);
	TextColor(rand()%7+8); gotoXY(32,6); putchar(2);
	TextColor(rand()%7+8); gotoXY(33,5); putchar(2);
	TextColor(rand()%7+8); gotoXY(34,5); putchar(2);
	TextColor(rand()%7+8); gotoXY(35,6); putchar(2);
	TextColor(rand()%7+8); gotoXY(36,7); putchar(2);
	TextColor(rand()%7+8); gotoXY(37,8); putchar(2);
	TextColor(rand()%7+8); gotoXY(38,9); putchar(2);
	TextColor(rand()%7+8); gotoXY(39,10); putchar(2);
	//in ra chu R
	TextColor(rand()%7+8); gotoXY(41,5); putchar(2);putchar(2);putchar(2);putchar(2);
	TextColor(rand()%7+8); gotoXY(41,6); putchar(2);
	TextColor(rand()%7+8); gotoXY(41,7); putchar(2);putchar(2);putchar(2);putchar(2);
	TextColor(rand()%7+8); gotoXY(41,8); putchar(2);putchar(2);
	TextColor(rand()%7+8); gotoXY(41,9); putchar(2);
	TextColor(rand()%7+8); gotoXY(41,10); putchar(2);
	TextColor(rand()%7+8); gotoXY(45,6); putchar(2);
	TextColor(rand()%7+8); gotoXY(45,7); putchar(2);
	TextColor(rand()%7+8); gotoXY(44,9); putchar(2);
	TextColor(rand()%7+8); gotoXY(45,10); putchar(2);
	//in ra chu O
	TextColor(rand()%7+8); gotoXY(47,6); putchar(2);
	TextColor(rand()%7+8); gotoXY(47,7); putchar(2);
	TextColor(rand()%7+8); gotoXY(47,8); putchar(2);
	TextColor(rand()%7+8); gotoXY(47,9); putchar(2);
	TextColor(rand()%7+8); gotoXY(48,5); putchar(2);putchar(2);putchar(2);putchar(2);
	TextColor(rand()%7+8); gotoXY(48,10); putchar(2);putchar(2);putchar(2);putchar(2);
	TextColor(rand()%7+8); gotoXY(52,6); putchar(2);
	TextColor(rand()%7+8); gotoXY(52,7); putchar(2);
	TextColor(rand()%7+8); gotoXY(52,8); putchar(2);
	TextColor(rand()%7+8); gotoXY(52,9); putchar(2);
}

void Menu(int &vt){
	//-menu
	TextColor(15);
	gotoXY(30,14);printf("  P1 vs computer");
	gotoXY(30,16);printf("  Exit Game");	
	gotoXY(30,12);printf("  P1 vs P2");
	switch(vt){
		case 0: 			
				TextColor(12);gotoXY(30,12);printf("  P1 vs P2");
				break;
		case 1:	
				TextColor(12);gotoXY(30,14);printf("  P1 vs computer");
				break;
		case 2: 
				TextColor(12);gotoXY(30,16);printf("  Exit Game");
				break;			
	}
	gotoXY(41,16);
}

void GUI(int &vt){
	char key;
	do{
		NameGame();
		if(_kbhit())
		{
			key=getch();
			switch(key){
				case 72 :  if(vt==0) vt=2; else vt--;  break;
				case 'w':  if(vt==0) vt=2; else vt--;  break;
				case 80:  if(vt==2) vt=0; else vt++;  break;
				case 's':  if(vt==2) vt=0; else vt++;  break;
			}
			Menu(vt);
		}
	}while(key!=13&&key!=32);
}

void Sound(){
	
}
void P1vsP2(ToaDo &M){
	while(1){
	//dieu khien		
	if(M.number%2)						//ban dau M.number=1
		Control_P1(M);					//P1 di truoc	
	else 
		Control_P2(M);					//P2 di sau
	//endgame
	if(CheckEndGame(M))
		{
			gotoXY(50,24);	TextColor (15); printf(" * Player %d win",M.data[M.x][M.y]);
			gotoXY(M.x,M.y);
			EndGame(M);
		}	
	}
}
int main(){
	ToaDo M;	
	int vt=0;		
	Menu(vt);
	//-----------------------
	do
	{
		GUI(vt);
		switch(vt)
		{
			case 0: NewGame(15,21,M);  P1vsP2(M);	break;
			case 1: break;
			case 2: exit(0);
		}
	}while(1);
	return 0;
}
