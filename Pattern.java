package LifeGame;

public enum Pattern {
	/*
	 * Pattern を各種集約する列挙体
	 */
	
	/*
	 * 振動子
	 */
	Pixel("Pixel"){

		@Override
		void setPattern(int[][] pattern) {
		}

	},
	Block("block"){

		@Override
		void setPattern(int[][] pattern) {
			pattern[0][0]=0xcc00;
		}

	},
	Beehive("Beehive"){

		@Override
		void setPattern(int[][] pattern) {
			pattern[0][0]=0x6960;
		}

	},
	Pulsar("Pulsar", 2, 1){

		@Override
		void setPattern(int[][] pattern) {
			pattern[0][0]=0xf800;
			pattern[1][0]=0x8800;
		}

	},
	Galaxy("Galaxy", 3, 3){

		@Override
		void setPattern(int[][] pattern) {
			pattern[0][0]=0xddcc;
			pattern[0][1]=0xcc0f;
			pattern[0][2]=0xf000;
			pattern[1][0]=0xff01;
			pattern[1][1]=0x111d;
			pattern[1][2]=0xd000;
			pattern[2][0]=0x8808;
			pattern[2][1]=0x8888;
			pattern[2][2]=0x8000;
		}
	},
	Unix("Unix", 2, 2){

		@Override
		void setPattern(int[][] pattern) {
			pattern[0][0]=0x6604;
			pattern[0][1]=0xa903;
			pattern[1][1]=0x3b0;
		}

	},
	VolcanoM("volcanoM", 4, 3){

		@Override
		void setPattern(int[][] pattern) {
			pattern[0][0]=0x1787;
			pattern[1][0]=0xf77a;
			pattern[2][0]=0xc70e;
			pattern[3][0]=0x88;
			pattern[0][1]=0xbd0;
			pattern[1][1]=0x559;
			pattern[2][1]=0x1464;
			pattern[1][2]=0x6000;
			pattern[2][2]=0x4600;
		}

	},
	ElectricFence("electric fence", 16, 4){

		@Override
		void setPattern(int[][] pattern) {
			pattern[0][0]=0x25;
			pattern[0][1]=0x5d54;
			pattern[0][2]=0x2100;
			pattern[1][0]=0x12;
			pattern[1][1]=0x2a2c;
			pattern[1][2]=0x7865;
			pattern[2][0]=0x25d1;
			pattern[2][1]=0xcb23;
			pattern[2][2]=0x2aba;
			pattern[2][3]=0x1000;
			pattern[3][0]=0x8;
			pattern[3][1]=0xe40;
			pattern[3][2]=0x5455;
			pattern[3][3]=0x8000;
			pattern[4][0]=0x4a;
			pattern[4][1]=0x4083;
			pattern[4][2]=0xe198;
			pattern[5][1]=0x80b;
			pattern[5][2]=0x3804;
			pattern[5][3]=0xc000;
			pattern[6][1]=0xc1;
			pattern[6][2]=0x1c00;
			pattern[7][1]=0x68;
			pattern[7][2]=0x8600;
			pattern[8][1]=0x3c;
			pattern[8][2]=0xc300;
			pattern[9][0]=0xc45;
			pattern[9][1]=0x2107;
			pattern[9][2]=0x7001;
			pattern[9][3]=0x230;
			pattern[10][0]=0x4c;
			pattern[10][1]=0x38d0;
			pattern[10][2]=0x622a;
			pattern[10][3]=0x9800;
			pattern[11][0]=0x8e;
			pattern[11][1]=0x1997;
			pattern[11][2]=0x4008;
			pattern[11][3]=0x8000;
			pattern[12][0]=0x12;
			pattern[12][1]=0x454b;
			pattern[12][2]=0x2010;
			pattern[13][0]=0x8;
			pattern[13][1]=0x8baa;
			pattern[13][2]=0x81a0;
			pattern[14][0]=0x64;
			pattern[14][1]=0x1383;
			pattern[14][2]=0x4100;
			pattern[15][2]=0x8800;
		}

	},
	
	/*
	 * Die Hard
	 */
	Acorn("acorn", 2, 1){

		@Override
		void setPattern(int[][] pattern) {
			pattern[0][0]=0x41c0;
			pattern[1][0]=0xe0;
		}
	},
	Rabit("rabit", 2, 1){

		@Override
		void setPattern(int[][] pattern) {
			pattern[0][0]=0x8e40;
			pattern[1][0]=0xe400;
		}

	},
	
	/*
	 * 移動物体
	 */
	Glider("glider"){

		@Override
		void setPattern(int[][] pattern) {
			pattern[0][0]=0xe840;
		}
	},
	SpaceCraftS("SpaceCraftS", 2, 1){

		@Override
		void setPattern(int[][] pattern) {
			pattern[0][0]=0x488f;
			pattern[1][0]=0x8080;
		}
	},
	SpaceCraftM("SpaceCraftM", 2, 2){

		@Override
		void setPattern(int[][] pattern) {
			pattern[0][0]=0x1488;
			pattern[0][1]=0xf000;
			pattern[1][0]=0x0404;
			pattern[1][1]=0x8000;
		}
	},
	SpaceCraftL("SpaceCraftL", 2, 2){

		@Override
		void setPattern(int[][] pattern) {
			pattern[0][0]=0x1488;
			pattern[0][1]=0xf000;
			pattern[1][0]=0x8202;
			pattern[1][1]=0xc000;
		}
	},
	SchickEngine("Schick engine", 3, 3){

		@Override
		void setPattern(int[][] pattern) {
			pattern[0][0]=0xc;
			pattern[0][1]=0xec00;
			pattern[1][0]=0x7809;
			pattern[1][1]=0x908;
			pattern[1][2]=0x7000;
			pattern[2][0]=0x8880;
			pattern[2][1]=0x88;
			pattern[2][2]=0x8000;
		}

	},
	Dart("dart", 3, 4){

		@Override
		void setPattern(int[][] pattern) {
			pattern[1][0]=0x130;
			pattern[2][0]=0x8404;
			pattern[0][1]=0x259;
			pattern[1][1]=0x4430;
			pattern[2][1]=0x40c0;
			pattern[0][2]=0x5200;
			pattern[1][2]=0x3440;
			pattern[2][2]=0xc044;
			pattern[1][3]=0x3100;
			pattern[2][3]=0x480;
		}

	},
	BrinkerShip("blinker ship", 7, 4){

		@Override
		void setPattern(int[][] pattern) {
			pattern[0][0]=0x6;
			pattern[0][1]=0xd730;
			pattern[0][2]=0x37d6;
			pattern[1][1]=0x8812;
			pattern[1][2]=0x1880;
			pattern[2][0]=0x3221;
			pattern[2][1]=0x862;
			pattern[2][2]=0x6801;
			pattern[2][3]=0x2230;
			pattern[3][0]=0xc202;
			pattern[3][2]=0x2;
			pattern[3][3]=0x2c0;
			pattern[4][1]=0x11;
			pattern[4][2]=0x1000;
			pattern[6][1]=0xea;
			pattern[6][2]=0xe000;
		}

	},
	Dragon("dragon", 8, 4){

		@Override
		void setPattern(int[][] pattern) {
			pattern[0][0]=0x78;
			pattern[0][1]=0x7000;
			pattern[0][2]=0x787;
			pattern[1][0]=0x4c1;
			pattern[1][1]=0xd400;
			pattern[1][2]=0x4d1c;
			pattern[1][3]=0x4000;
			pattern[2][0]=0x72a;
			pattern[2][1]=0xc8cc;
			pattern[2][2]=0x8ca2;
			pattern[2][3]=0x7000;
			pattern[3][0]=0x4b10;
			pattern[3][2]=0x1;
			pattern[3][3]=0xb400;
			pattern[4][0]=0xcf06;
			pattern[4][1]=0x3000;
			pattern[4][2]=0x360;
			pattern[4][3]=0xfc00;
			pattern[5][0]=0xee;
			pattern[5][1]=0xc1dd;
			pattern[5][2]=0x1cee;
			pattern[6][0]=0x9905;
			pattern[6][1]=0x5088;
			pattern[6][2]=0x550;
			pattern[6][3]=0x9900;
			pattern[7][0]=0xcc0c;
			pattern[7][1]=0xc000;
			pattern[7][2]=0xcc0;
			pattern[7][3]=0xcc00;
		}

	},
	SpaceShip("spaceship c/5", 13, 13){

		@Override
		void setPattern(int[][] pattern) {
			pattern[0][0]=0x1;
			pattern[0][1]=0x3660;
			pattern[0][2]=0x7;
			pattern[0][3]=0x7ae0;
			pattern[0][4]=0x4010;
			pattern[1][0]=0x6e8;
			pattern[1][1]=0x3088;
			pattern[1][2]=0x732a;
			pattern[1][3]=0x1181;
			pattern[1][4]=0x4804;
			pattern[1][5]=0x4000;
			pattern[2][0]=0x111;
			pattern[2][1]=0x18fc;
			pattern[2][2]=0x104;
			pattern[2][3]=0xf084;
			pattern[2][4]=0x1000;
			pattern[3][0]=0x6ae8;
			pattern[3][1]=0x200d;
			pattern[3][2]=0xa988;
			pattern[3][3]=0x3088;
			pattern[3][4]=0x88ea;
			pattern[3][5]=0xb5c0;
			pattern[4][0]=0x802;
			pattern[4][1]=0x4900;
			pattern[4][2]=0x8;
			pattern[4][3]=0xf230;
			pattern[4][5]=0x1ed4;
			pattern[4][6]=0xc2c;
			pattern[4][7]=0x4400;
			pattern[5][1]=0x800;
			pattern[5][3]=0xa68c;
			pattern[5][4]=0x674a;
			pattern[5][5]=0x240;
			pattern[5][6]=0x9203;
			pattern[5][7]=0x7304;
			pattern[5][8]=0x111;
			pattern[6][4]=0x5520;
			pattern[6][5]=0x8059;
			pattern[6][6]=0x400;
			pattern[6][7]=0x88;
			pattern[6][8]=0x3000;
			pattern[6][9]=0x6400;
			pattern[7][4]=0xc00;
			pattern[7][5]=0x9cc;
			pattern[7][6]=0x3000;
			pattern[7][8]=0xc380;
			pattern[7][9]=0xa2e4;
			pattern[7][10]=0x10;
			pattern[8][5]=0x7;
			pattern[8][6]=0x88;
			pattern[8][7]=0xa844;
			pattern[8][9]=0xcd;
			pattern[8][10]=0x20c;
			pattern[9][6]=0xc80;
			pattern[9][7]=0xa3e0;
			pattern[9][8]=0x3301;
			pattern[9][9]=0x2080;
			pattern[9][10]=0x6d40;
			pattern[9][11]=0x2122;
			pattern[9][12]=0x1000;
			pattern[10][7]=0x2;
			pattern[10][8]=0x1140;
			pattern[10][9]=0x4e84;
			pattern[10][10]=0x4a40;
			pattern[10][11]=0xc51;
			pattern[10][12]=0x243;
			pattern[11][9]=0xb4;
			pattern[11][10]=0x4603;
			pattern[11][11]=0x36c8;
			pattern[11][12]=0x400;
			pattern[12][9]=0x8;
			pattern[12][10]=0x251;
			pattern[12][11]=0x400;
		}

	},
	Cordership("Cordership", 16, 15){

		@Override
		void setPattern(int[][] pattern) {
			pattern[0][6]=0x7d52;
			pattern[1][5]=0x4;
			pattern[1][6]=0xbdd3;
			pattern[1][7]=0x6310;
			pattern[1][10]=0xcc0;
			pattern[2][6]=0x80c;
			pattern[2][7]=0xee00;
			pattern[2][10]=0x6100;
			pattern[3][6]=0x7713;
			pattern[3][9]=0x8;
			pattern[3][10]=0x1850;
			pattern[3][12]=0xcc0;
			pattern[4][6]=0x80;
			pattern[4][9]=0x1;
			pattern[4][10]=0x2880;
			pattern[5][3]=0xee0b;
			pattern[5][4]=0x8300;
			pattern[5][7]=0xcc0;
			pattern[5][9]=0x2fb;
			pattern[5][10]=0x80e6;
			pattern[5][14]=0xcc0;
			pattern[6][0]=0x10;
			pattern[6][4]=0x8800;
			pattern[6][9]=0x3100;
			pattern[6][10]=0x66;
			pattern[6][12]=0x7d52;
			pattern[7][0]=0xc3;
			pattern[7][1]=0x3100;
			pattern[7][5]=0x25;
			pattern[7][6]=0x5200;
			pattern[7][9]=0x8800;
			pattern[7][11]=0x4;
			pattern[7][12]=0xbdd3;
			pattern[7][13]=0x6310;
			pattern[8][0]=0x8;
			pattern[8][1]=0x8000;
			pattern[8][12]=0x80c;
			pattern[8][13]=0xee00;
			pattern[9][0]=0x342;
			pattern[9][1]=0x1000;
			pattern[9][4]=0x66;
			pattern[9][12]=0x8ce3;
			pattern[10][0]=0x3103;
			pattern[10][1]=0x1100;
			pattern[10][3]=0x4a9;
			pattern[10][4]=0x6000;
			pattern[10][8]=0x13;
			pattern[10][9]=0x2335;
			pattern[10][10]=0x2310;
			pattern[10][12]=0xd730;
			pattern[11][0]=0x8e;
			pattern[11][1]=0x2c0;
			pattern[11][8]=0x8;
			pattern[11][9]=0x488a;
			pattern[11][10]=0x7d77;
			pattern[11][11]=0x3000;
			pattern[11][12]=0xcc80;
			pattern[12][10]=0x800;
			pattern[13][1]=0x66;
			pattern[13][6]=0x7d52;
			pattern[14][5]=0x4;
			pattern[14][6]=0xbdd3;
			pattern[14][7]=0x6310;
			pattern[15][3]=0x66;
			pattern[15][6]=0x80c;
			pattern[15][7]=0xee00;
		}

	},
	
	/*
	 * 移動物体発射装置
	 */
	Shuttle("shuttle", 1, 2){

		@Override
		void setPattern(int[][] pattern) {
			pattern[0][0]=0x8c67;
			pattern[0][1]=0x6c80;
		}
	},
	SwichEngine("swichengine", 2, 1){

		@Override
		void setPattern(int[][] pattern) {
			pattern[0][0]=0x5841;
			pattern[1][0]=0x008c;
		}
	},
	GliderGun("glider gun", 9, 3){

		@Override
		void setPattern(int[][] pattern) {
			pattern[0][1]=0xcc00;
			pattern[2][0]=0x0001;
			pattern[2][1]=0x2221;
			pattern[3][0]=0x00c1;
			pattern[3][1]=0x0201;
			pattern[3][2]=0xc000;
			pattern[4][1]=0x8c80;
			pattern[5][0]=0x02cc;
			pattern[5][1]=0xc200;
			pattern[6][0]=0x8800;
			pattern[6][1]=0x0880;
			pattern[8][0]=0x0033;
		}
	},
	B52Bomber("B-52 bomber", 10, 6){

		@Override
		void setPattern(int[][] pattern) {
			pattern[0][0]=0x6600;
			pattern[4][0]=0x10;
			pattern[5][0]=0x848;
			pattern[8][0]=0x24;
			pattern[9][0]=0x80;
			pattern[0][1]=0xcd11;
			pattern[2][1]=0x6600;
			pattern[6][1]=0x11;
			pattern[7][1]=0x8;
			pattern[8][1]=0x2210;
			pattern[9][1]=0x4a2c;
			pattern[0][2]=0x9600;
			pattern[5][2]=0x6470;
			pattern[7][2]=0x8000;
			pattern[9][2]=0xc;
			pattern[0][3]=0x69a;
			pattern[1][3]=0x8;
			pattern[5][3]=0x5;
			pattern[7][3]=0xc;
			pattern[8][3]=0x1;
			pattern[9][3]=0xc008;
			pattern[0][4]=0x4020;
			pattern[1][4]=0x8480;
			pattern[4][4]=0x252;
			pattern[5][4]=0x3200;
			pattern[7][4]=0xc000;
			pattern[8][4]=0x1001;
			pattern[9][4]=0xa222;
			pattern[9][5]=0xc000;
		}

	},
	
	/*
	 * 大規模振動子
	 */
	Period51("Period51", 8, 8){

		@Override
		void setPattern(int[][] pattern) {
			pattern[0][0]=0x110c;
			pattern[1][0]=0x8800;
			pattern[2][0]=0xee0c;
			pattern[3][0]=0x80;
			pattern[4][0]=0x20;
			pattern[5][0]=0xee06;
			pattern[6][0]=0x3300;
			pattern[7][0]=0x6;
			pattern[0][1]=0xc000;
			pattern[2][1]=0x8401;
			pattern[3][1]=0x4448;
			pattern[4][1]=0x4443;
			pattern[5][1]=0x2400;
			pattern[7][1]=0x6000;
			pattern[0][2]=0xddc0;
			pattern[1][2]=0x8401;
			pattern[5][2]=0x1;
			pattern[6][2]=0x3500;
			pattern[7][2]=0x6660;
			pattern[0][3]=0x2000;
			pattern[1][3]=0x1e00;
			pattern[5][3]=0x1000;
			pattern[6][3]=0xe00;
			pattern[7][3]=0x8000;
			pattern[0][4]=0x20;
			pattern[1][4]=0xe11;
			pattern[5][4]=0x11;
			pattern[6][4]=0xe00;
			pattern[7][4]=0x80;
			pattern[0][5]=0xcdd0;
			pattern[1][5]=0x480;
			pattern[2][5]=0x1;
			pattern[3][5]=0x8;
			pattern[4][5]=0x3;
			pattern[6][5]=0x530;
			pattern[7][5]=0x6660;
			pattern[0][6]=0xcc;
			pattern[2][6]=0x48c;
			pattern[3][6]=0x4440;
			pattern[4][6]=0x4440;
			pattern[5][6]=0x426;
			pattern[7][6]=0x66;
			pattern[0][7]=0x110;
			pattern[1][7]=0x880;
			pattern[2][7]=0xee0;
			pattern[3][7]=0x8000;
			pattern[4][7]=0x2000;
			pattern[5][7]=0xee0;
			pattern[6][7]=0x330;
		}

	},
	Hectic("hectic", 10, 10){

		@Override
		void setPattern(int[][] pattern) {
			pattern[0][3]=0xc;
			pattern[0][4]=0xc000;
			pattern[1][3]=0x124;
			pattern[1][4]=0x2100;
			pattern[2][3]=0x4488;
			pattern[2][4]=0x8440;
			pattern[3][5]=0x12;
			pattern[3][6]=0x43c0;
			pattern[3][9]=0x110;
			pattern[4][4]=0xac4;
			pattern[4][5]=0x8;
			pattern[4][6]=0x4860;
			pattern[4][9]=0x880;
			pattern[5][0]=0x3300;
			pattern[5][3]=0xc342;
			pattern[5][4]=0x1004;
			pattern[5][5]=0x6a00;
			pattern[6][3]=0x6848;
			pattern[7][5]=0x4522;
			pattern[7][6]=0x2540;
			pattern[8][5]=0x84;
			pattern[8][6]=0x8000;
			pattern[9][5]=0x66;
		}

	},
	TrafficCircle("traffic circle", 12, 12){

		@Override
		void setPattern(int[][] pattern) {
			pattern[0][4]=0xc;
			pattern[0][5]=0x9700;
			pattern[0][6]=0x79c0;
			pattern[1][3]=0x13;
			pattern[1][4]=0x3008;
			pattern[1][5]=0x8c00;
			pattern[1][6]=0xc880;
			pattern[2][3]=0x695a;
			pattern[2][4]=0x8000;
			pattern[2][5]=0x2220;
			pattern[3][4]=0xe0;
			pattern[3][6]=0xe000;
			pattern[3][9]=0x69a4;
			pattern[3][10]=0x200;
			pattern[4][5]=0x8991;
			pattern[4][9]=0x1088;
			pattern[4][10]=0x4801;
			pattern[4][11]=0x11;
			pattern[5][0]=0x6513;
			pattern[5][1]=0x7100;
			pattern[5][3]=0x300;
			pattern[5][4]=0x7;
			pattern[5][6]=0x700;
			pattern[5][8]=0xe0;
			pattern[5][9]=0x2000;
			pattern[5][10]=0x4c;
			pattern[5][11]=0xc448;
			pattern[6][0]=0x1223;
			pattern[6][1]=0x3200;
			pattern[6][2]=0x222;
			pattern[6][3]=0x802;
			pattern[6][4]=0x2200;
			pattern[6][5]=0x444;
			pattern[6][10]=0x8e;
			pattern[6][11]=0xc8a6;
			pattern[7][0]=0x8800;
			pattern[7][1]=0x8012;
			pattern[7][2]=0x200;
			pattern[7][3]=0xe00;
			pattern[7][6]=0x111;
			pattern[8][1]=0xc;
			pattern[8][2]=0x2596;
			pattern[8][5]=0x7;
			pattern[8][7]=0x700;
			pattern[9][6]=0x444;
			pattern[9][7]=0x13;
			pattern[9][8]=0x5a96;
			pattern[10][5]=0x113;
			pattern[10][6]=0x31;
			pattern[10][7]=0x1088;
			pattern[10][8]=0x8000;
			pattern[11][5]=0x39e;
			pattern[11][6]=0xe9;
			pattern[11][7]=0x3000;
		}

	},
	PiOrbital("pi orbital", 15, 15){

		@Override
		void setPattern(int[][] pattern) {
			pattern[0][7]=0x6a;
			pattern[0][8]=0xa512;
			pattern[0][9]=0x2222;
			pattern[0][10]=0x215a;
			pattern[0][11]=0xa600;
			pattern[1][2]=0x1000;
			pattern[1][4]=0x1;
			pattern[1][5]=0x1000;
			pattern[1][8]=0xc404;
			pattern[1][9]=0x220;
			pattern[1][10]=0x404c;
			pattern[1][12]=0x10;
			pattern[2][1]=0x8;
			pattern[2][2]=0x1b00;
			pattern[2][4]=0x8;
			pattern[2][5]=0x8000;
			pattern[2][6]=0x4;
			pattern[2][7]=0xa026;
			pattern[2][8]=0x7620;
			pattern[2][9]=0x59;
			pattern[2][10]=0x5000;
			pattern[2][11]=0x4dfc;
			pattern[2][12]=0x4048;
			pattern[3][0]=0x3470;
			pattern[3][1]=0x1100;
			pattern[3][2]=0xce8c;
			pattern[3][3]=0xc000;
			pattern[3][8]=0xcc00;
			pattern[3][11]=0x880;
			pattern[4][0]=0xa0c;
			pattern[4][1]=0x3800;
			pattern[4][2]=0x83;
			pattern[4][3]=0x3100;
			pattern[4][12]=0x11;
			pattern[5][0]=0xc100;
			pattern[5][1]=0xf000;
			pattern[5][2]=0x8028;
			pattern[5][3]=0x8000;
			pattern[5][12]=0x88;
			pattern[6][0]=0x343c;
			pattern[6][1]=0x2600;
			pattern[6][2]=0x7f2;
			pattern[6][3]=0x6600;
			pattern[6][12]=0x100;
			pattern[7][0]=0x880;
			pattern[7][2]=0x21a0;
			pattern[7][12]=0xb180;
			pattern[7][14]=0x3210;
			pattern[8][4]=0x11;
			pattern[8][5]=0x110;
			pattern[8][11]=0xcc8;
			pattern[8][12]=0xec00;
			pattern[8][13]=0xc96;
			pattern[8][14]=0x8580;
			pattern[9][1]=0x3;
			pattern[9][2]=0x3000;
			pattern[9][4]=0xc2;
			pattern[9][5]=0x12c0;
			pattern[9][11]=0x133;
			pattern[9][12]=0x8020;
			pattern[9][13]=0xf0;
			pattern[9][14]=0x60;
			pattern[10][11]=0x88;
			pattern[10][12]=0x2000;
			pattern[10][13]=0x396;
			pattern[10][14]=0x1a10;
			pattern[11][3]=0x3300;
			pattern[11][4]=0x11;
			pattern[11][5]=0x1000;
			pattern[11][6]=0x670;
			pattern[11][11]=0x66;
			pattern[11][12]=0x2f70;
			pattern[11][14]=0xc480;
			pattern[12][1]=0x2;
			pattern[12][2]=0x5046;
			pattern[12][3]=0xe640;
			pattern[12][4]=0x42;
			pattern[12][5]=0x4000;
			pattern[12][6]=0x8ccc;
			pattern[12][7]=0x80a4;
			pattern[12][9]=0x33;
			pattern[12][12]=0xa12;
			pattern[13][3]=0x6;
			pattern[13][4]=0x5140;
			pattern[13][5]=0x8804;
			pattern[13][6]=0x1560;
			pattern[14][3]=0xcaa;
			pattern[14][4]=0x4088;
			pattern[14][5]=0x8888;
			pattern[14][6]=0x4aa;
			pattern[14][7]=0xc000;
		}

	},
	
	/*
	 * エデン配置
	 */
	GardenOfEden("Garden of Eden", 4, 4){

		@Override
		void setPattern(int[][] pattern) {
			pattern[0][0]=0xdbfe;
			pattern[0][1]=0x7f5b;
			pattern[0][2]=0xfbe7;
			pattern[0][3]=0xeb00;
			pattern[1][0]=0x5b7a;
			pattern[1][1]=0x5e7b;
			pattern[1][2]=0xd7f5;
			pattern[1][3]=0xaf00;
			pattern[2][0]=0x6b6b;
			pattern[2][1]=0xdff5;
			pattern[2][2]=0xfdf5;
			pattern[2][3]=0xbf00;
			pattern[3][0]=0x848c;
			pattern[3][1]=0x8484;
			pattern[3][2]=0x848c;
			pattern[3][3]=0x4c00;
		}

	};

	/*
	 * name		そのPattern の名称
	 * pattern	実際に情報を内包する
	 */
	private String name;
	private int[][] pattern;
	
	/*
	 * Pattern の内部を実装する
	 */
	abstract void setPattern(int[][] pattern);

	/*
	 * 名前のみを指定するコンストラクタ
	 * Size はx, y ともに1 となる
	 */
	private Pattern(String n){
		this(n, 1, 1);
	}
	/*
	 * 名前とサイズを指定するコンストラクタ
	 */
	private Pattern(String n, int w, int h){
		name=n;
		pattern=new int[w][h];
		setPattern(pattern);
	}

	/*
	 * 名前を返す
	 */
	public String toString(){
		return name;
	}
	/*
	 * Pattern を返す
	 */
	public int[][] getPattern(){
		return pattern;
	}
}
