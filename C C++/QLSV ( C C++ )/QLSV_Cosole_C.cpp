#include<stdio.h>
#include<stdlib.h>
#include<conio.h>
#include <string.h>
#include<math.h>
#include <time.h>
//#include <iostream>
#include <windows.h>
//using namespace std;
//============================================================================================
void gotoXY (int column, int line)
{
	COORD coord;
	coord.X = column;
	coord.Y = line;
	SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE),coord);
}
void TextColor (int color)
{
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE) , color);
}

enum {
	K_LEN 	=72,
	K_XUONG	=80,
	K_TRAI 	=75,
	K_PHAI	=77,
	K_ENTER	=13,
	K_SPACE	=32,
	K_ESC	=27,
	K_BACKSPACE =8,
	K_F1	=59,    	//HOAC ';'
	K_F2	=60,		//HOAC '<',	
	K_F3	=61,		//HOAC '=',
	K_F4	=62,		//HOAC '>',
	K_F5	=63,		//HOAC '?',
	K_F6	=64,		//HOAC '@',
	K_F7	=65,		//HOAC 'A',
	K_F8	=66,		//HOAC 'B',
	K_F9	=67,		//HOAC 'C',
	K_F10	=68,		//HOAC 'D',
	K_TAB	=9,
	K_END   =79,
	K_PGDN	=81,
	K_PGUP	=73,
	K_HOME	=71,
	K_INSERT=82,	
	K_DELETE=83,
	K_DEL	=46
}; 

typedef char Menu[32];
Menu menu[4] = {    "New       ",
                	"Open	   ",
                	"Thong tin ",
               		"Thoat     ",
			}; 
Menu menu1[6] = {   "Them     ",
                	"Xoa  	  ",
                	"Tim Kiem ",
                	"Sua      ",
                	"Save  	  ",
               		"Thoat    ",
			};			
int showMenu(Menu s[],int size,int color,int ToaDoX,int ToaDoY){
	char key;
	int vt=0;
	//=============in ra menu ================
	TextColor(15);
	for(int i=0; i<size; i++){
		gotoXY(ToaDoX,i+ToaDoY); puts(s[i]);
	}
	//------------------------------------
	do
	{
		gotoXY(ToaDoX,vt+ToaDoY); TextColor(color);puts(s[vt]); //in ra dong moi
		//======================================
		key=getch();
		gotoXY(ToaDoX,vt+ToaDoY); TextColor(15);puts(s[vt]); //xoa dong cu 
		switch(key){
			case K_LEN: 
							if(vt>0)	
								vt--; 
							else vt=size-1;
							break;
			case K_XUONG:  	
							if(vt<size-1)	vt++; 
							else vt=0;
							break;				
			case K_ENTER:  	return vt;					
			case K_BACKSPACE: return -1;	
		}
	}while(1);
}
 
//=================================================================================================================================
struct HocSinh{
	int number;
	char namefile[16];
	char Ten[32];
	char MS[16];
	float DiemGK;
	float DiemCK;
	float DTB;
}HS[100];

float DTB(float x, float y){
	return 0.3*x+ 0.7*y;
}
void Xuat(int number){	
	gotoXY(20,10+2*number); printf("|%s",HS[number].Ten);	
	gotoXY(40,10+2*number);printf("|%s",HS[number].MS);	
	gotoXY(50,10+2*number); printf("|%.2f",HS[number].DiemGK);	
	gotoXY(60,10+2*number); printf("|%-9.2f|",HS[number].DiemCK);
	gotoXY(70,10+2*number); printf("|%-8.2f|",HS[number].DTB);
	gotoXY(20,11+2*number);  printf("+-------------------+---------+---------+---------+--------+");
	//in ra cot
	gotoXY(17,10+2*number);  putchar(176);
	gotoXY(17,11+2*number);  putchar(176);
}
void TableHS(int number){
	system("cls");
	gotoXY(21,6); printf("BANG DANH SACH HOC SINH");
	gotoXY(20,7);  printf("+-------------------+---------+---------+---------+--------+");
	gotoXY(20,8);  printf("|TEN                | MSSV    | DIEM_GK | DIEM_CK |DIEM TB |");
	gotoXY(20,9);  printf("+-------------------+---------+---------+---------+--------+");
	for(int i=0;i<HS[0].number;i++){
		Xuat(i);
	}
} 

void  Nhap(int vt){
	// hien thong tin 
	gotoXY(20,1); printf("TEN: ");
	gotoXY(20,2); printf("MSSV: ");
	gotoXY(20,3); printf("Diem giua ky: ");
	gotoXY(20,4); printf("Diem cuoi ky: ");
	gotoXY(21,5); printf("--------------------------------------------");
	//nhap TEN
	do{
		gotoXY(35,1); printf("                                             ");
		gotoXY(35,1); gets(HS[vt].Ten);	
	}while(!strcmp( HS[vt].Ten , "" ) || strlen(HS[vt].Ten) >20);
	//MSSV
	int check;
	do{
		check=1;	
		gotoXY(35,2); printf("                                             ");
		gotoXY(35,2); gets(HS[vt].MS);
		for(int i=0;i<HS[0].number;i++){
			if(i==vt) continue;
			if(  !strcmp(HS[vt].MS , HS[i].MS) ){
				check=0;
				break;				
			}
		}
	}while(!check  ||  !(strcmp(HS[vt].MS , "")) || strlen(HS[vt].MS) >9 );
	//diem giua ky	
	char c;
	do{			
		gotoXY(35,3); printf("                                             ");
		gotoXY(35,3); 
		check =  scanf("%f%c",&HS[vt].DiemGK , &c )!=2 || c!='\n' ;				//nhap vao mot so va ngay sau so do la enter
		fflush(stdin);
	}while(HS[vt].DiemGK>10 || HS[vt].DiemGK<0 || check )	;
	//diem cuoi ky
	do{
		gotoXY(35,4); printf("                                             ");
		gotoXY(35,4); 
		check= scanf("%f%c",&HS[vt].DiemCK , &c )!=2 || c!='\n' ;
		fflush(stdin);
	}while(HS[vt].DiemCK>10 || HS[vt].DiemCK<0 || check);	
	
	fflush(stdin);	
	HS[vt].DTB= DTB(HS[vt].DiemGK , HS[vt].DiemCK);
	//finish
}

void Save(int number){		
	FILE *f;
		//--sava ten cac file
	if(!strcmp( HS[0].namefile , "" ))
	{
	while(!strcmp( HS[0].namefile , "" ))
	{
		gotoXY(20,1);printf("Dat ten file:          "); 
		gotoXY(35,1);gets(HS[0].namefile);
	}
		f=fopen("DATA","ab");
		fwrite(&HS[0].namefile,sizeof(char[16]),1,f);
		fclose(f);		
	}
	//--save DSSV------------------------------------------
		f=fopen(HS[0].namefile,"wb");
		fwrite(&HS[0],sizeof(HocSinh),number,f);
		fclose(f);
		printf("Da save %d SV \n",number);

}
int LoadDSFile(){
	char ten[16];
	FILE *f;
	if(( f = fopen("DATA", "rb"))==NULL)
	{
		printf("Khong co file nao");
		return 0;
	}
	else
	{
		fseek(f, 0, SEEK_END);					//con tro xuong cuoi file (so 0 co nghia la do doi` so voi END)
	   	int number = ftell(f)	 / sizeof(char[16]);
	   	fseek(f, 0, SEEK_SET);				//con tro len dau file
	   	//doc du lieu
	   	printf("DS cac file la: \n");
	   	for(int i=0;i<number;i++){
	  		fread(&ten, sizeof(char[16]), 1, f);
	   		puts(ten);
	   	}
		fclose(f);
		return number;   //tra ve so luong file
	}
}	
void Load(int number)
	{
		int x;
		FILE *f;
 		long length;
 		//---------------------------------------------------------
  		do{
  			system("cls");
  			x=LoadDSFile();
  			gotoXY(0,x+2); printf("Mo file (neu khong co file thi tao file moi): "); gets(HS[0].namefile);
  		}while ((f = fopen(HS[0].namefile, "rb")) == NULL && x!=0 || strlen(HS[0].namefile)==0);
  		//---
    	if(x)
  		{
   	 	fseek(f, 0, SEEK_END);					//con tro xuong cuoi file (so 0 co nghia la do doi` so voi END)
    	length = ftell(f);					//lay do dai file	
    	fseek(f, 0, SEEK_SET);				//con tro len dau file
    	number = length / sizeof(HocSinh);
    	//doc du lieu
    	fread(&HS, sizeof(HocSinh), number, f);
    	fclose(f);
   		}
}	  
void Infor(){
	system("cls");
	char s[64]={"Chuong trinh: Quan li sinh vienBy: Nguyen Tuan Vu Version: Demo"};
	//in ra 2 duong ke ngang
	for(int i=1;i<40;i++){
		gotoXY(i,0); 	putchar(196);
		gotoXY(i,9); 	putchar(196);
		Sleep(20);
	}
	//in ra 2 duong ke doc
for(int i=1;i<9;i++){
		gotoXY(0,i); putchar(179);				
		gotoXY(40,i); putchar(179);
		Sleep(20);
	}
	//in ra chuoi thong tin
for(int i=0;i<64;i++){
		if(i<31)		gotoXY(i+2,1);	
		else if(i<50) 	gotoXY(i-29,3);	
		else 			gotoXY(i-48,5);	
			putchar(s[i]);
			Sleep(100);
		}
		gotoXY(2,7);
		system("pause");
}

void Xoa(){
	//khai bao
	int vtxoa=0;
	int check=0;
	char MS[10];
	gotoXY(20,2); printf("Nhap MS can xoa:\n"); gotoXY(40,2); gets(MS);
	//process
	for(int i=0;i<HS[0].number;i++){
		if(!strcmp(MS,HS[i].MS)){
			vtxoa=i;
			check=1;
			}
	}
	if(check){
		for(int i=vtxoa;i<HS[0].number-1;i++){
			strcpy(HS[i].Ten,HS[i+1].Ten);
			strcpy(HS[i].MS,HS[i+1].MS);
			HS[i].DiemGK=HS[i+1].DiemGK;
			HS[i].DiemCK=HS[i+1].DiemCK;
			HS[i].DTB=HS[i+1].DTB;
		}
		HS[0].number--;
	}
	else{
		gotoXY(20,3);
		printf("Khong co MS nay`");
	} 
}
void KhoiTao(){
	TableHS(HS[0].number);
	gotoXY(0,9); printf("TEN FILE");
	gotoXY(0,10); puts(HS[0].namefile);		
	//in ra duong ke doc
	for(int i=0;i<40;i++){
			gotoXY(17,i); putchar(176);
		}
	//in ra duong ngang
	gotoXY(0,8); printf("=================");
	gotoXY(0,0); printf("---MENU---");
}
int TimKiem(){
	char MS[16];
	int check=0;
	gotoXY(20,1); printf("Nhap MSSV: "); gets(MS);
	gotoXY(20,2); printf("KET QUA: ");
	if(HS[0].number==0){
		gotoXY(20,3); 
		printf("khong tim thay");
		return -1;
	} 
	for(int i=0; i<HS[0].number;i++){
		if( !strcmp(MS, HS[i].MS) )	{
			gotoXY(20,3); printf("TEN: %-20sMS: %-10sGK:%-6.2fCK:%-6.2f",HS[i].Ten,HS[i].MS,HS[i].DiemGK,HS[i].DiemCK);
			check=1;
			return i;
		}		
		}
	if(!check) {
		gotoXY(20,3); 
		printf("khong tim thay");
		return -1;
}
}
void Sua(){
	int vt =TimKiem();
	if(vt!=-1){
		char key;
		do{
			gotoXY(20,4); printf("Ban co muon sua thong tin khong(y/n)");
			key=getch();
		}while(key!='N' &&key!= 'Y'&&key!='n' &&key!= 'y');
		//sua
		if(key=='y' || key=='Y'){
			TextColor(12);
			//xoa
			gotoXY(20,1); printf("                                                            ");
			gotoXY(20,2); printf("                                                            ");
			gotoXY(20,3); printf("                                                            ");
			gotoXY(20,4); printf("                                                            ");
			gotoXY(20,5); printf("                                                            ");
			gotoXY(20,0); printf("Sua thong tin cho MSSV: "); puts(HS[vt].MS);
			Nhap(vt);
		}	
	}
	else getch();
	TextColor(15);
}
void New(){
	char name[16];
	while(1){
		KhoiTao();
	//start		
		switch(showMenu(menu1,6,12,1,2)+1){
			case 1: Nhap(HS[0].number);	HS[0].number++;	TableHS(HS[0].number); break;
			case 2: Xoa(); break;
			case 3: TimKiem();getch(); break;
			case 4: Sua(); break;
			case 5: Save(HS[0].number); 
			case 6: exit(0);
		}
}
}

void ChuQ(int x,int y){
	//7 x
	gotoXY(x,y-1); putchar(176);
	gotoXY(x,y-2); putchar(176);
	gotoXY(x+1,y-3); putchar(176);putchar(176);putchar(176);
	gotoXY(x+1,y); putchar(176);putchar(176);putchar(176);
	gotoXY(x+3,y-1); putchar(176); putchar(176); 
	gotoXY(x+4,y-2); putchar(176);
	gotoXY(x+5,y); putchar(176);
}
void ChuU(int x,int y){
	//5 x
	gotoXY(x,y); putchar(176);
	gotoXY(x,y-1); putchar(176);
	gotoXY(x,y-2); putchar(176);
	gotoXY(x,y-3); putchar(176);
	gotoXY(x+1,y); putchar(176);putchar(176);putchar(176);
	gotoXY(x+3,y); putchar(176);
	gotoXY(x+3,y-1); putchar(176);
	gotoXY(x+3,y-2); putchar(176);
	gotoXY(x+3,y-3); putchar(176);
}
void ChuA(int x,int y){
	//8 dong
	gotoXY(x,y); putchar(176);
	gotoXY(x+1,y-1); putchar(176);putchar(196);putchar(176);putchar(196);
	gotoXY(x+2,y-2); putchar(176);
	gotoXY(x+3,y-3); putchar(176);
	gotoXY(x+4,y-2); putchar(176);
	gotoXY(x+5,y-1); putchar(176);
	gotoXY(x+6,y); putchar(176);
}
void ChuN(int x,int y){
	// 5x
	gotoXY(x,y); putchar(176);
	gotoXY(x,y-1); putchar(176);
	gotoXY(x,y-2); putchar(176);
	gotoXY(x,y-3); putchar(176);
	gotoXY(x+1,y-2); putchar(176);
	gotoXY(x+2,y-1); putchar(176);
	gotoXY(x+3,y); putchar(176);
	gotoXY(x+3,y-1); putchar(176);
	gotoXY(x+3,y-2); putchar(176);
	gotoXY(x+3,y-3); putchar(176);
}
void ChuL(int x, int y){
	//5x
	gotoXY(x,y); putchar(176); putchar(176); putchar(176); putchar(176);
	gotoXY(x,y-1); putchar(176);
	gotoXY(x,y-2); putchar(176);
	gotoXY(x,y-3); putchar(176);
}
ChuY(int x,int y){
	//6x
	gotoXY(x,y-3); putchar(176);
	gotoXY(x+1,y-2); putchar(176);
	gotoXY(x+2,y-1); putchar(176);
	gotoXY(x+3,y-2); putchar(176);
	gotoXY(x+4,y-3); putchar(176);
	gotoXY(x+2,y-1); putchar(176);
	gotoXY(x+2,y); putchar(176);
}
ChuS(int x,int y){
	
	gotoXY(x,y); putchar(176);putchar(176);putchar(176);
	gotoXY(x+1,y-2); putchar(176);putchar(176);
	gotoXY(x,y-3); putchar(176);
	gotoXY(x+1,y-4); putchar(176);putchar(176);putchar(176);
	gotoXY(x+3,y-1); putchar(176);
}
ChuV(int x,int y){
	gotoXY(x,y-3); putchar(176);
	gotoXY(x+1,y-2); putchar(176);
	gotoXY(x+2,y-1); putchar(176);
	gotoXY(x+3,y); putchar(176);
	gotoXY(x+4,y-1); putchar(176);
	gotoXY(x+5,y-2); putchar(176);
	gotoXY(x+6,y-3); putchar(176);
}
void TenChuongTrinh(){
	TextColor(12);
	ChuQ(13,7);
	ChuU(20,7);
	gotoXY(29,0); putchar(176);
	gotoXY(30,1); putchar(176);
	gotoXY(29,2); putchar(176);
	ChuA(25,7);
	ChuN(33,7);
	ChuL(40,7);
	gotoXY(47,1); putchar(176);
	gotoXY(46,2); putchar(176);
	ChuY(44,7);
	ChuS(53,7);
	ChuV(58,7);
}
int main(){
	while(1){
		TenChuongTrinh();
		switch(showMenu(menu,4,12,35,10)+1){		
			case 1: system("cls");    New();	break;
			case 2: system("cls"); Load(HS[0].number) ;  New();  break;
			case 3: Infor(); system("cls");  break;
			case 4: exit(0);			
		}
}
}
