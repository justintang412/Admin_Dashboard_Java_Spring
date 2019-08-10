package com.yczx.support;

import java.util.HashMap;
import java.util.Map;

public class GmBean {
	private String f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16, f17, f18, f19, f20, f21, f22,
			f23, f24, f25, f26, f27, f28, f29, f30, f31, f32, f33, f34, f35, f36, f37, f38, f39, f40, f41, f42, f43,
			f44, f45, f46, f47, f48, f49, f50, f51, f52, f53, f54, f55, f56, f57, f58, f59, f60, f61, f62, f63, f64,
			f65, f66, f67, f68, f69, f70, f71, f72, f73, f74, f75, f76, f77, f78, f79, f80, f81, f82, f83, f84, f85,
			f86, f87, f88, f89, f90, f91, f92, f93, f94, f95, f96, f97, f98, f99, f100, f101, f102, f103, f104, f105,
			f106, f107, f108, f109, f110, f111, f112, f113, f114, f115, f116, f117, f118, f119, f120, f121, f122, f123,
			f124, f125, f126, f127, f128, f129, f130, f131, f132, f133, f134, f135, f136, f137, f138, f139, f140, f141,
			f142, f143, f144, f145, f146, f147, f148, f149, f150, f151, f152, f153, f154, f155, f156, f157, f158, f159,
			f160, f161, f162, f163, f164, f165, f166, f167, f168, f169, f170, f171, f172, f173, f174, f175, f176, f177,
			f178, f179, f180, f181, f182, f183, f184, f185, f186, f187, f188, f189, f190, f191, f192, f193, f194, f195,
			f196, f197, f198, f199, f200;

	private boolean empty = true;

	public GmBean() {

	}

	public void setupValues(String[] values) {
		for (int i = 1; i < values.length + 1; i++) {
			this.setValueByIndex(i, values[i - 1]);
		}
	}

	public Map<String, Object> getParameterMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 1; i < 201; i++) {
			String name = "f" + i;
			String value = this.getValueByIndex(i);
			if (value == null) {
				break;
			} else {
				map.put(name, value);
			}
		}
		return map;
	}

	public String getValueByIndex(int i) {

		if (i < 201 && i > 0) {
			switch (i) {
			case 1:
				return f1;
			case 2:
				return f2;
			case 3:
				return f3;
			case 4:
				return f4;
			case 5:
				return f5;
			case 6:
				return f6;
			case 7:
				return f7;
			case 8:
				return f8;
			case 9:
				return f9;
			case 10:
				return f10;
			case 11:
				return f11;
			case 12:
				return f12;
			case 13:
				return f13;
			case 14:
				return f14;
			case 15:
				return f15;
			case 16:
				return f16;
			case 17:
				return f17;
			case 18:
				return f18;
			case 19:
				return f19;
			case 20:
				return f20;
			case 21:
				return f21;
			case 22:
				return f22;
			case 23:
				return f23;
			case 24:
				return f24;
			case 25:
				return f25;
			case 26:
				return f26;
			case 27:
				return f27;
			case 28:
				return f28;
			case 29:
				return f29;
			case 30:
				return f30;
			case 31:
				return f31;
			case 32:
				return f32;
			case 33:
				return f33;
			case 34:
				return f34;
			case 35:
				return f35;
			case 36:
				return f36;
			case 37:
				return f37;
			case 38:
				return f38;
			case 39:
				return f39;
			case 40:
				return f40;
			case 41:
				return f41;
			case 42:
				return f42;
			case 43:
				return f43;
			case 44:
				return f44;
			case 45:
				return f45;
			case 46:
				return f46;
			case 47:
				return f47;
			case 48:
				return f48;
			case 49:
				return f49;
			case 50:
				return f50;
			case 51:
				return f51;
			case 52:
				return f52;
			case 53:
				return f53;
			case 54:
				return f54;
			case 55:
				return f55;
			case 56:
				return f56;
			case 57:
				return f57;
			case 58:
				return f58;
			case 59:
				return f59;
			case 60:
				return f60;
			case 61:
				return f61;
			case 62:
				return f62;
			case 63:
				return f63;
			case 64:
				return f64;
			case 65:
				return f65;
			case 66:
				return f66;
			case 67:
				return f67;
			case 68:
				return f68;
			case 69:
				return f69;
			case 70:
				return f70;
			case 71:
				return f71;
			case 72:
				return f72;
			case 73:
				return f73;
			case 74:
				return f74;
			case 75:
				return f75;
			case 76:
				return f76;
			case 77:
				return f77;
			case 78:
				return f78;
			case 79:
				return f79;
			case 80:
				return f80;
			case 81:
				return f81;
			case 82:
				return f82;
			case 83:
				return f83;
			case 84:
				return f84;
			case 85:
				return f85;
			case 86:
				return f86;
			case 87:
				return f87;
			case 88:
				return f88;
			case 89:
				return f89;
			case 90:
				return f90;
			case 91:
				return f91;
			case 92:
				return f92;
			case 93:
				return f93;
			case 94:
				return f94;
			case 95:
				return f95;
			case 96:
				return f96;
			case 97:
				return f97;
			case 98:
				return f98;
			case 99:
				return f99;
			case 100:
				return f100;
			case 101:
				return f101;
			case 102:
				return f102;
			case 103:
				return f103;
			case 104:
				return f104;
			case 105:
				return f105;
			case 106:
				return f106;
			case 107:
				return f107;
			case 108:
				return f108;
			case 109:
				return f109;
			case 110:
				return f110;
			case 111:
				return f111;
			case 112:
				return f112;
			case 113:
				return f113;
			case 114:
				return f114;
			case 115:
				return f115;
			case 116:
				return f116;
			case 117:
				return f117;
			case 118:
				return f118;
			case 119:
				return f119;
			case 120:
				return f120;
			case 121:
				return f121;
			case 122:
				return f122;
			case 123:
				return f123;
			case 124:
				return f124;
			case 125:
				return f125;
			case 126:
				return f126;
			case 127:
				return f127;
			case 128:
				return f128;
			case 129:
				return f129;
			case 130:
				return f130;
			case 131:
				return f131;
			case 132:
				return f132;
			case 133:
				return f133;
			case 134:
				return f134;
			case 135:
				return f135;
			case 136:
				return f136;
			case 137:
				return f137;
			case 138:
				return f138;
			case 139:
				return f139;
			case 140:
				return f140;
			case 141:
				return f141;
			case 142:
				return f142;
			case 143:
				return f143;
			case 144:
				return f144;
			case 145:
				return f145;
			case 146:
				return f146;
			case 147:
				return f147;
			case 148:
				return f148;
			case 149:
				return f149;
			case 150:
				return f150;
			case 151:
				return f151;
			case 152:
				return f152;
			case 153:
				return f153;
			case 154:
				return f154;
			case 155:
				return f155;
			case 156:
				return f156;
			case 157:
				return f157;
			case 158:
				return f158;
			case 159:
				return f159;
			case 160:
				return f160;
			case 161:
				return f161;
			case 162:
				return f162;
			case 163:
				return f163;
			case 164:
				return f164;
			case 165:
				return f165;
			case 166:
				return f166;
			case 167:
				return f167;
			case 168:
				return f168;
			case 169:
				return f169;
			case 170:
				return f170;
			case 171:
				return f171;
			case 172:
				return f172;
			case 173:
				return f173;
			case 174:
				return f174;
			case 175:
				return f175;
			case 176:
				return f176;
			case 177:
				return f177;
			case 178:
				return f178;
			case 179:
				return f179;
			case 180:
				return f180;
			case 181:
				return f181;
			case 182:
				return f182;
			case 183:
				return f183;
			case 184:
				return f184;
			case 185:
				return f185;
			case 186:
				return f186;
			case 187:
				return f187;
			case 188:
				return f188;
			case 189:
				return f189;
			case 190:
				return f190;
			case 191:
				return f191;
			case 192:
				return f192;
			case 193:
				return f193;
			case 194:
				return f194;
			case 195:
				return f195;
			case 196:
				return f196;
			case 197:
				return f197;
			case 198:
				return f198;
			case 199:
				return f199;
			case 200:
				return f200;
			}
			return null;
		} else {
			throw new RuntimeException("out of index of GmBean");
		}
	}

	public void setValueByIndex(int i, String value) {
		if (i < 201 && i > 0) {
			empty = false;
			switch (i) {
			case 1:
				f1 = value;
				break;
			case 2:
				f2 = value;
				break;
			case 3:
				f3 = value;
				break;
			case 4:
				f4 = value;
				break;
			case 5:
				f5 = value;
				break;
			case 6:
				f6 = value;
				break;
			case 7:
				f7 = value;
				break;
			case 8:
				f8 = value;
				break;
			case 9:
				f9 = value;
				break;
			case 10:
				f10 = value;
				break;
			case 11:
				f11 = value;
				break;
			case 12:
				f12 = value;
				break;
			case 13:
				f13 = value;
				break;
			case 14:
				f14 = value;
				break;
			case 15:
				f15 = value;
				break;
			case 16:
				f16 = value;
				break;
			case 17:
				f17 = value;
				break;
			case 18:
				f18 = value;
				break;
			case 19:
				f19 = value;
				break;
			case 20:
				f20 = value;
				break;
			case 21:
				f21 = value;
				break;
			case 22:
				f22 = value;
				break;
			case 23:
				f23 = value;
				break;
			case 24:
				f24 = value;
				break;
			case 25:
				f25 = value;
				break;
			case 26:
				f26 = value;
				break;
			case 27:
				f27 = value;
				break;
			case 28:
				f28 = value;
				break;
			case 29:
				f29 = value;
				break;
			case 30:
				f30 = value;
				break;
			case 31:
				f31 = value;
				break;
			case 32:
				f32 = value;
				break;
			case 33:
				f33 = value;
				break;
			case 34:
				f34 = value;
				break;
			case 35:
				f35 = value;
				break;
			case 36:
				f36 = value;
				break;
			case 37:
				f37 = value;
				break;
			case 38:
				f38 = value;
				break;
			case 39:
				f39 = value;
				break;
			case 40:
				f40 = value;
				break;
			case 41:
				f41 = value;
				break;
			case 42:
				f42 = value;
				break;
			case 43:
				f43 = value;
				break;
			case 44:
				f44 = value;
				break;
			case 45:
				f45 = value;
				break;
			case 46:
				f46 = value;
				break;
			case 47:
				f47 = value;
				break;
			case 48:
				f48 = value;
				break;
			case 49:
				f49 = value;
				break;
			case 50:
				f50 = value;
				break;
			case 51:
				f51 = value;
				break;
			case 52:
				f52 = value;
				break;
			case 53:
				f53 = value;
				break;
			case 54:
				f54 = value;
				break;
			case 55:
				f55 = value;
				break;
			case 56:
				f56 = value;
				break;
			case 57:
				f57 = value;
				break;
			case 58:
				f58 = value;
				break;
			case 59:
				f59 = value;
				break;
			case 60:
				f60 = value;
				break;
			case 61:
				f61 = value;
				break;
			case 62:
				f62 = value;
				break;
			case 63:
				f63 = value;
				break;
			case 64:
				f64 = value;
				break;
			case 65:
				f65 = value;
				break;
			case 66:
				f66 = value;
				break;
			case 67:
				f67 = value;
				break;
			case 68:
				f68 = value;
				break;
			case 69:
				f69 = value;
				break;
			case 70:
				f70 = value;
				break;
			case 71:
				f71 = value;
				break;
			case 72:
				f72 = value;
				break;
			case 73:
				f73 = value;
				break;
			case 74:
				f74 = value;
				break;
			case 75:
				f75 = value;
				break;
			case 76:
				f76 = value;
				break;
			case 77:
				f77 = value;
				break;
			case 78:
				f78 = value;
				break;
			case 79:
				f79 = value;
				break;
			case 80:
				f80 = value;
				break;
			case 81:
				f81 = value;
				break;
			case 82:
				f82 = value;
				break;
			case 83:
				f83 = value;
				break;
			case 84:
				f84 = value;
				break;
			case 85:
				f85 = value;
				break;
			case 86:
				f86 = value;
				break;
			case 87:
				f87 = value;
				break;
			case 88:
				f88 = value;
				break;
			case 89:
				f89 = value;
				break;
			case 90:
				f90 = value;
				break;
			case 91:
				f91 = value;
				break;
			case 92:
				f92 = value;
				break;
			case 93:
				f93 = value;
				break;
			case 94:
				f94 = value;
				break;
			case 95:
				f95 = value;
				break;
			case 96:
				f96 = value;
				break;
			case 97:
				f97 = value;
				break;
			case 98:
				f98 = value;
				break;
			case 99:
				f99 = value;
				break;
			case 100:
				f100 = value;
				break;
			case 101:
				f101 = value;
				break;
			case 102:
				f102 = value;
				break;
			case 103:
				f103 = value;
				break;
			case 104:
				f104 = value;
				break;
			case 105:
				f105 = value;
				break;
			case 106:
				f106 = value;
				break;
			case 107:
				f107 = value;
				break;
			case 108:
				f108 = value;
				break;
			case 109:
				f109 = value;
				break;
			case 110:
				f110 = value;
				break;
			case 111:
				f111 = value;
				break;
			case 112:
				f112 = value;
				break;
			case 113:
				f113 = value;
				break;
			case 114:
				f114 = value;
				break;
			case 115:
				f115 = value;
				break;
			case 116:
				f116 = value;
				break;
			case 117:
				f117 = value;
				break;
			case 118:
				f118 = value;
				break;
			case 119:
				f119 = value;
				break;
			case 120:
				f120 = value;
				break;
			case 121:
				f121 = value;
				break;
			case 122:
				f122 = value;
				break;
			case 123:
				f123 = value;
				break;
			case 124:
				f124 = value;
				break;
			case 125:
				f125 = value;
				break;
			case 126:
				f126 = value;
				break;
			case 127:
				f127 = value;
				break;
			case 128:
				f128 = value;
				break;
			case 129:
				f129 = value;
				break;
			case 130:
				f130 = value;
				break;
			case 131:
				f131 = value;
				break;
			case 132:
				f132 = value;
				break;
			case 133:
				f133 = value;
				break;
			case 134:
				f134 = value;
				break;
			case 135:
				f135 = value;
				break;
			case 136:
				f136 = value;
				break;
			case 137:
				f137 = value;
				break;
			case 138:
				f138 = value;
				break;
			case 139:
				f139 = value;
				break;
			case 140:
				f140 = value;
				break;
			case 141:
				f141 = value;
				break;
			case 142:
				f142 = value;
				break;
			case 143:
				f143 = value;
				break;
			case 144:
				f144 = value;
				break;
			case 145:
				f145 = value;
				break;
			case 146:
				f146 = value;
				break;
			case 147:
				f147 = value;
				break;
			case 148:
				f148 = value;
				break;
			case 149:
				f149 = value;
				break;
			case 150:
				f150 = value;
				break;
			case 151:
				f151 = value;
				break;
			case 152:
				f152 = value;
				break;
			case 153:
				f153 = value;
				break;
			case 154:
				f154 = value;
				break;
			case 155:
				f155 = value;
				break;
			case 156:
				f156 = value;
				break;
			case 157:
				f157 = value;
				break;
			case 158:
				f158 = value;
				break;
			case 159:
				f159 = value;
				break;
			case 160:
				f160 = value;
				break;
			case 161:
				f161 = value;
				break;
			case 162:
				f162 = value;
				break;
			case 163:
				f163 = value;
				break;
			case 164:
				f164 = value;
				break;
			case 165:
				f165 = value;
				break;
			case 166:
				f166 = value;
				break;
			case 167:
				f167 = value;
				break;
			case 168:
				f168 = value;
				break;
			case 169:
				f169 = value;
				break;
			case 170:
				f170 = value;
				break;
			case 171:
				f171 = value;
				break;
			case 172:
				f172 = value;
				break;
			case 173:
				f173 = value;
				break;
			case 174:
				f174 = value;
				break;
			case 175:
				f175 = value;
				break;
			case 176:
				f176 = value;
				break;
			case 177:
				f177 = value;
				break;
			case 178:
				f178 = value;
				break;
			case 179:
				f179 = value;
				break;
			case 180:
				f180 = value;
				break;
			case 181:
				f181 = value;
				break;
			case 182:
				f182 = value;
				break;
			case 183:
				f183 = value;
				break;
			case 184:
				f184 = value;
				break;
			case 185:
				f185 = value;
				break;
			case 186:
				f186 = value;
				break;
			case 187:
				f187 = value;
				break;
			case 188:
				f188 = value;
				break;
			case 189:
				f189 = value;
				break;
			case 190:
				f190 = value;
				break;
			case 191:
				f191 = value;
				break;
			case 192:
				f192 = value;
				break;
			case 193:
				f193 = value;
				break;
			case 194:
				f194 = value;
				break;
			case 195:
				f195 = value;
				break;
			case 196:
				f196 = value;
				break;
			case 197:
				f197 = value;
				break;
			case 198:
				f198 = value;
				break;
			case 199:
				f199 = value;
				break;
			case 200:
				f200 = value;
				break;
			}
		} else {
			throw new RuntimeException("out of index of GmBean");
		}

	}

	public String getF1() {
		return f1;
	}

	public void setF1(String f1) {
		this.f1 = f1;
		empty = false;
	}

	public String getF2() {
		return f2;
	}

	public void setF2(String f2) {
		this.f2 = f2;
		empty = false;
	}

	public String getF3() {
		return f3;
	}

	public void setF3(String f3) {
		this.f3 = f3;
		empty = false;
	}

	public String getF4() {
		return f4;
	}

	public void setF4(String f4) {
		this.f4 = f4;
		empty = false;
	}

	public String getF5() {
		return f5;
	}

	public void setF5(String f5) {
		this.f5 = f5;
		empty = false;
	}

	public String getF6() {
		return f6;
	}

	public void setF6(String f6) {
		this.f6 = f6;
		empty = false;
	}

	public String getF7() {
		return f7;
	}

	public void setF7(String f7) {
		this.f7 = f7;
		empty = false;
	}

	public String getF8() {
		return f8;
	}

	public void setF8(String f8) {
		this.f8 = f8;
		empty = false;
	}

	public String getF9() {
		return f9;
	}

	public void setF9(String f9) {
		this.f9 = f9;
		empty = false;
	}

	public String getF10() {
		return f10;
	}

	public void setF10(String f10) {
		this.f10 = f10;
		empty = false;
	}

	public String getF11() {
		return f11;
	}

	public void setF11(String f11) {
		this.f11 = f11;
		empty = false;
	}

	public String getF12() {
		return f12;
	}

	public void setF12(String f12) {
		this.f12 = f12;
		empty = false;
	}

	public String getF13() {
		return f13;
	}

	public void setF13(String f13) {
		this.f13 = f13;
		empty = false;
	}

	public String getF14() {
		return f14;
	}

	public void setF14(String f14) {
		this.f14 = f14;
		empty = false;
	}

	public String getF15() {
		return f15;
	}

	public void setF15(String f15) {
		this.f15 = f15;
		empty = false;
	}

	public String getF16() {
		return f16;
	}

	public void setF16(String f16) {
		this.f16 = f16;
		empty = false;
	}

	public String getF17() {
		return f17;
	}

	public void setF17(String f17) {
		this.f17 = f17;
		empty = false;
	}

	public String getF18() {
		return f18;
	}

	public void setF18(String f18) {
		this.f18 = f18;
		empty = false;
	}

	public String getF19() {
		return f19;
	}

	public void setF19(String f19) {
		this.f19 = f19;
		empty = false;
	}

	public String getF20() {
		return f20;
	}

	public void setF20(String f20) {
		this.f20 = f20;
		empty = false;
	}

	public String getF21() {
		return f21;
	}

	public void setF21(String f21) {
		this.f21 = f21;
		empty = false;
	}

	public String getF22() {
		return f22;
	}

	public void setF22(String f22) {
		this.f22 = f22;
		empty = false;
	}

	public String getF23() {
		return f23;
	}

	public void setF23(String f23) {
		this.f23 = f23;
		empty = false;
	}

	public String getF24() {
		return f24;
	}

	public void setF24(String f24) {
		this.f24 = f24;
		empty = false;
	}

	public String getF25() {
		return f25;
	}

	public void setF25(String f25) {
		this.f25 = f25;
		empty = false;
	}

	public String getF26() {
		return f26;
	}

	public void setF26(String f26) {
		this.f26 = f26;
		empty = false;
	}

	public String getF27() {
		return f27;
	}

	public void setF27(String f27) {
		this.f27 = f27;
		empty = false;
	}

	public String getF28() {
		return f28;
	}

	public void setF28(String f28) {
		this.f28 = f28;
		empty = false;
	}

	public String getF29() {
		return f29;
	}

	public void setF29(String f29) {
		this.f29 = f29;
		empty = false;
	}

	public String getF30() {
		return f30;
	}

	public void setF30(String f30) {
		this.f30 = f30;
		empty = false;
	}

	public String getF31() {
		return f31;
	}

	public void setF31(String f31) {
		this.f31 = f31;
		empty = false;
	}

	public String getF32() {
		return f32;
	}

	public void setF32(String f32) {
		this.f32 = f32;
		empty = false;
	}

	public String getF33() {
		return f33;
	}

	public void setF33(String f33) {
		this.f33 = f33;
		empty = false;
	}

	public String getF34() {
		return f34;
	}

	public void setF34(String f34) {
		this.f34 = f34;
		empty = false;
	}

	public String getF35() {
		return f35;
	}

	public void setF35(String f35) {
		this.f35 = f35;
		empty = false;
	}

	public String getF36() {
		return f36;
	}

	public void setF36(String f36) {
		this.f36 = f36;
		empty = false;
	}

	public String getF37() {
		return f37;
	}

	public void setF37(String f37) {
		this.f37 = f37;
		empty = false;
	}

	public String getF38() {
		return f38;
	}

	public void setF38(String f38) {
		this.f38 = f38;
		empty = false;
	}

	public String getF39() {
		return f39;
	}

	public void setF39(String f39) {
		this.f39 = f39;
		empty = false;
	}

	public String getF40() {
		return f40;
	}

	public void setF40(String f40) {
		this.f40 = f40;
		empty = false;
	}

	public String getF41() {
		return f41;
	}

	public void setF41(String f41) {
		this.f41 = f41;
		empty = false;
	}

	public String getF42() {
		return f42;
	}

	public void setF42(String f42) {
		this.f42 = f42;
		empty = false;
	}

	public String getF43() {
		return f43;
	}

	public void setF43(String f43) {
		this.f43 = f43;
		empty = false;
	}

	public String getF44() {
		return f44;
	}

	public void setF44(String f44) {
		this.f44 = f44;
		empty = false;
	}

	public String getF45() {
		return f45;
	}

	public void setF45(String f45) {
		this.f45 = f45;
		empty = false;
	}

	public String getF46() {
		return f46;
	}

	public void setF46(String f46) {
		this.f46 = f46;
		empty = false;
	}

	public String getF47() {
		return f47;
	}

	public void setF47(String f47) {
		this.f47 = f47;
		empty = false;
	}

	public String getF48() {
		return f48;
	}

	public void setF48(String f48) {
		this.f48 = f48;
		empty = false;
	}

	public String getF49() {
		return f49;
	}

	public void setF49(String f49) {
		this.f49 = f49;
		empty = false;
	}

	public String getF50() {
		return f50;
	}

	public void setF50(String f50) {
		this.f50 = f50;
		empty = false;
	}

	public String getF51() {
		return f51;
	}

	public void setF51(String f51) {
		this.f51 = f51;
		empty = false;
	}

	public String getF52() {
		return f52;
	}

	public void setF52(String f52) {
		this.f52 = f52;
		empty = false;
	}

	public String getF53() {
		return f53;
	}

	public void setF53(String f53) {
		this.f53 = f53;
		empty = false;
	}

	public String getF54() {
		return f54;
	}

	public void setF54(String f54) {
		this.f54 = f54;
		empty = false;
	}

	public String getF55() {
		return f55;
	}

	public void setF55(String f55) {
		this.f55 = f55;
		empty = false;
	}

	public String getF56() {
		return f56;
	}

	public void setF56(String f56) {
		this.f56 = f56;
		empty = false;
	}

	public String getF57() {
		return f57;
	}

	public void setF57(String f57) {
		this.f57 = f57;
		empty = false;
	}

	public String getF58() {
		return f58;
	}

	public void setF58(String f58) {
		this.f58 = f58;
		empty = false;
	}

	public String getF59() {
		return f59;
	}

	public void setF59(String f59) {
		this.f59 = f59;
		empty = false;
	}

	public String getF60() {
		return f60;
	}

	public void setF60(String f60) {
		this.f60 = f60;
		empty = false;
	}

	public String getF61() {
		return f61;
	}

	public void setF61(String f61) {
		this.f61 = f61;
		empty = false;
	}

	public String getF62() {
		return f62;
	}

	public void setF62(String f62) {
		this.f62 = f62;
		empty = false;
	}

	public String getF63() {
		return f63;
	}

	public void setF63(String f63) {
		this.f63 = f63;
		empty = false;
	}

	public String getF64() {
		return f64;
	}

	public void setF64(String f64) {
		this.f64 = f64;
		empty = false;
	}

	public String getF65() {
		return f65;
	}

	public void setF65(String f65) {
		this.f65 = f65;
		empty = false;
	}

	public String getF66() {
		return f66;
	}

	public void setF66(String f66) {
		this.f66 = f66;
		empty = false;
	}

	public String getF67() {
		return f67;
	}

	public void setF67(String f67) {
		this.f67 = f67;
		empty = false;
	}

	public String getF68() {
		return f68;
	}

	public void setF68(String f68) {
		this.f68 = f68;
		empty = false;
	}

	public String getF69() {
		return f69;
	}

	public void setF69(String f69) {
		this.f69 = f69;
		empty = false;
	}

	public String getF70() {
		return f70;
	}

	public void setF70(String f70) {
		this.f70 = f70;
		empty = false;
	}

	public String getF71() {
		return f71;
	}

	public void setF71(String f71) {
		this.f71 = f71;
		empty = false;
	}

	public String getF72() {
		return f72;
	}

	public void setF72(String f72) {
		this.f72 = f72;
		empty = false;
	}

	public String getF73() {
		return f73;
	}

	public void setF73(String f73) {
		this.f73 = f73;
		empty = false;
	}

	public String getF74() {
		return f74;
	}

	public void setF74(String f74) {
		this.f74 = f74;
		empty = false;
	}

	public String getF75() {
		return f75;
	}

	public void setF75(String f75) {
		this.f75 = f75;
		empty = false;
	}

	public String getF76() {
		return f76;
	}

	public void setF76(String f76) {
		this.f76 = f76;
		empty = false;
	}

	public String getF77() {
		return f77;
	}

	public void setF77(String f77) {
		this.f77 = f77;
		empty = false;
	}

	public String getF78() {
		return f78;
	}

	public void setF78(String f78) {
		this.f78 = f78;
		empty = false;
	}

	public String getF79() {
		return f79;
	}

	public void setF79(String f79) {
		this.f79 = f79;
		empty = false;
	}

	public String getF80() {
		return f80;
	}

	public void setF80(String f80) {
		this.f80 = f80;
		empty = false;
	}

	public String getF81() {
		return f81;
	}

	public void setF81(String f81) {
		this.f81 = f81;
		empty = false;
	}

	public String getF82() {
		return f82;
	}

	public void setF82(String f82) {
		this.f82 = f82;
		empty = false;
	}

	public String getF83() {
		return f83;
	}

	public void setF83(String f83) {
		this.f83 = f83;
		empty = false;
	}

	public String getF84() {
		return f84;
	}

	public void setF84(String f84) {
		this.f84 = f84;
		empty = false;
	}

	public String getF85() {
		return f85;
	}

	public void setF85(String f85) {
		this.f85 = f85;
		empty = false;
	}

	public String getF86() {
		return f86;
	}

	public void setF86(String f86) {
		this.f86 = f86;
		empty = false;
	}

	public String getF87() {
		return f87;
	}

	public void setF87(String f87) {
		this.f87 = f87;
		empty = false;
	}

	public String getF88() {
		return f88;
	}

	public void setF88(String f88) {
		this.f88 = f88;
		empty = false;
	}

	public String getF89() {
		return f89;
	}

	public void setF89(String f89) {
		this.f89 = f89;
		empty = false;
	}

	public String getF90() {
		return f90;
	}

	public void setF90(String f90) {
		this.f90 = f90;
		empty = false;
	}

	public String getF91() {
		return f91;
	}

	public void setF91(String f91) {
		this.f91 = f91;
		empty = false;
	}

	public String getF92() {
		return f92;
	}

	public void setF92(String f92) {
		this.f92 = f92;
		empty = false;
	}

	public String getF93() {
		return f93;
	}

	public void setF93(String f93) {
		this.f93 = f93;
		empty = false;
	}

	public String getF94() {
		return f94;
	}

	public void setF94(String f94) {
		this.f94 = f94;
		empty = false;
	}

	public String getF95() {
		return f95;
	}

	public void setF95(String f95) {
		this.f95 = f95;
		empty = false;
	}

	public String getF96() {
		return f96;
	}

	public void setF96(String f96) {
		this.f96 = f96;
		empty = false;
	}

	public String getF97() {
		return f97;
	}

	public void setF97(String f97) {
		this.f97 = f97;
		empty = false;
	}

	public String getF98() {
		return f98;
	}

	public void setF98(String f98) {
		this.f98 = f98;
		empty = false;
	}

	public String getF99() {
		return f99;
	}

	public void setF99(String f99) {
		this.f99 = f99;
		empty = false;
	}

	public String getF100() {
		return f100;
	}

	public void setF100(String f100) {
		this.f100 = f100;
		empty = false;
	}

	public String getF101() {
		return f101;
	}

	public void setF101(String f101) {
		this.f101 = f101;
		empty = false;
	}

	public String getF102() {
		return f102;
	}

	public void setF102(String f102) {
		this.f102 = f102;
		empty = false;
	}

	public String getF103() {
		return f103;
	}

	public void setF103(String f103) {
		this.f103 = f103;
		empty = false;
	}

	public String getF104() {
		return f104;
	}

	public void setF104(String f104) {
		this.f104 = f104;
		empty = false;
	}

	public String getF105() {
		return f105;
	}

	public void setF105(String f105) {
		this.f105 = f105;
		empty = false;
	}

	public String getF106() {
		return f106;
	}

	public void setF106(String f106) {
		this.f106 = f106;
		empty = false;
	}

	public String getF107() {
		return f107;
	}

	public void setF107(String f107) {
		this.f107 = f107;
		empty = false;
	}

	public String getF108() {
		return f108;
	}

	public void setF108(String f108) {
		this.f108 = f108;
		empty = false;
	}

	public String getF109() {
		return f109;
	}

	public void setF109(String f109) {
		this.f109 = f109;
		empty = false;
	}

	public String getF110() {
		return f110;
	}

	public void setF110(String f110) {
		this.f110 = f110;
		empty = false;
	}

	public String getF111() {
		return f111;
	}

	public void setF111(String f111) {
		this.f111 = f111;
		empty = false;
	}

	public String getF112() {
		return f112;
	}

	public void setF112(String f112) {
		this.f112 = f112;
		empty = false;
	}

	public String getF113() {
		return f113;
	}

	public void setF113(String f113) {
		this.f113 = f113;
		empty = false;
	}

	public String getF114() {
		return f114;
	}

	public void setF114(String f114) {
		this.f114 = f114;
		empty = false;
	}

	public String getF115() {
		return f115;
	}

	public void setF115(String f115) {
		this.f115 = f115;
		empty = false;
	}

	public String getF116() {
		return f116;
	}

	public void setF116(String f116) {
		this.f116 = f116;
		empty = false;
	}

	public String getF117() {
		return f117;
	}

	public void setF117(String f117) {
		this.f117 = f117;
		empty = false;
	}

	public String getF118() {
		return f118;
	}

	public void setF118(String f118) {
		this.f118 = f118;
		empty = false;
	}

	public String getF119() {
		return f119;
	}

	public void setF119(String f119) {
		this.f119 = f119;
		empty = false;
	}

	public String getF120() {
		return f120;
	}

	public void setF120(String f120) {
		this.f120 = f120;
		empty = false;
	}

	public String getF121() {
		return f121;
	}

	public void setF121(String f121) {
		this.f121 = f121;
		empty = false;
	}

	public String getF122() {
		return f122;
	}

	public void setF122(String f122) {
		this.f122 = f122;
		empty = false;
	}

	public String getF123() {
		return f123;
	}

	public void setF123(String f123) {
		this.f123 = f123;
		empty = false;
	}

	public String getF124() {
		return f124;
	}

	public void setF124(String f124) {
		this.f124 = f124;
		empty = false;
	}

	public String getF125() {
		return f125;
	}

	public void setF125(String f125) {
		this.f125 = f125;
		empty = false;
	}

	public String getF126() {
		return f126;
	}

	public void setF126(String f126) {
		this.f126 = f126;
		empty = false;
	}

	public String getF127() {
		return f127;
	}

	public void setF127(String f127) {
		this.f127 = f127;
		empty = false;
	}

	public String getF128() {
		return f128;
	}

	public void setF128(String f128) {
		this.f128 = f128;
		empty = false;
	}

	public String getF129() {
		return f129;
	}

	public void setF129(String f129) {
		this.f129 = f129;
		empty = false;
	}

	public String getF130() {
		return f130;
	}

	public void setF130(String f130) {
		this.f130 = f130;
		empty = false;
	}

	public String getF131() {
		return f131;
	}

	public void setF131(String f131) {
		this.f131 = f131;
		empty = false;
	}

	public String getF132() {
		return f132;
	}

	public void setF132(String f132) {
		this.f132 = f132;
		empty = false;
	}

	public String getF133() {
		return f133;
	}

	public void setF133(String f133) {
		this.f133 = f133;
		empty = false;
	}

	public String getF134() {
		return f134;
	}

	public void setF134(String f134) {
		this.f134 = f134;
		empty = false;
	}

	public String getF135() {
		return f135;
	}

	public void setF135(String f135) {
		this.f135 = f135;
		empty = false;
	}

	public String getF136() {
		return f136;
	}

	public void setF136(String f136) {
		this.f136 = f136;
		empty = false;
	}

	public String getF137() {
		return f137;
	}

	public void setF137(String f137) {
		this.f137 = f137;
		empty = false;
	}

	public String getF138() {
		return f138;
	}

	public void setF138(String f138) {
		this.f138 = f138;
		empty = false;
	}

	public String getF139() {
		return f139;
	}

	public void setF139(String f139) {
		this.f139 = f139;
		empty = false;
	}

	public String getF140() {
		return f140;
	}

	public void setF140(String f140) {
		this.f140 = f140;
		empty = false;
	}

	public String getF141() {
		return f141;
	}

	public void setF141(String f141) {
		this.f141 = f141;
		empty = false;
	}

	public String getF142() {
		return f142;
	}

	public void setF142(String f142) {
		this.f142 = f142;
		empty = false;
	}

	public String getF143() {
		return f143;
	}

	public void setF143(String f143) {
		this.f143 = f143;
		empty = false;
	}

	public String getF144() {
		return f144;
	}

	public void setF144(String f144) {
		this.f144 = f144;
		empty = false;
	}

	public String getF145() {
		return f145;
	}

	public void setF145(String f145) {
		this.f145 = f145;
		empty = false;
	}

	public String getF146() {
		return f146;
	}

	public void setF146(String f146) {
		this.f146 = f146;
		empty = false;
	}

	public String getF147() {
		return f147;
	}

	public void setF147(String f147) {
		this.f147 = f147;
		empty = false;
	}

	public String getF148() {
		return f148;
	}

	public void setF148(String f148) {
		this.f148 = f148;
		empty = false;
	}

	public String getF149() {
		return f149;
	}

	public void setF149(String f149) {
		this.f149 = f149;
		empty = false;
	}

	public String getF150() {
		return f150;
	}

	public void setF150(String f150) {
		this.f150 = f150;
		empty = false;
	}

	public String getF151() {
		return f151;
	}

	public void setF151(String f151) {
		this.f151 = f151;
		empty = false;
	}

	public String getF152() {
		return f152;
	}

	public void setF152(String f152) {
		this.f152 = f152;
		empty = false;
	}

	public String getF153() {
		return f153;
	}

	public void setF153(String f153) {
		this.f153 = f153;
		empty = false;
	}

	public String getF154() {
		return f154;
	}

	public void setF154(String f154) {
		this.f154 = f154;
		empty = false;
	}

	public String getF155() {
		return f155;
	}

	public void setF155(String f155) {
		this.f155 = f155;
		empty = false;
	}

	public String getF156() {
		return f156;
	}

	public void setF156(String f156) {
		this.f156 = f156;
		empty = false;
	}

	public String getF157() {
		return f157;
	}

	public void setF157(String f157) {
		this.f157 = f157;
		empty = false;
	}

	public String getF158() {
		return f158;
	}

	public void setF158(String f158) {
		this.f158 = f158;
		empty = false;
	}

	public String getF159() {
		return f159;
	}

	public void setF159(String f159) {
		this.f159 = f159;
		empty = false;
	}

	public String getF160() {
		return f160;
	}

	public void setF160(String f160) {
		this.f160 = f160;
		empty = false;
	}

	public String getF161() {
		return f161;
	}

	public void setF161(String f161) {
		this.f161 = f161;
		empty = false;
	}

	public String getF162() {
		return f162;
	}

	public void setF162(String f162) {
		this.f162 = f162;
		empty = false;
	}

	public String getF163() {
		return f163;
	}

	public void setF163(String f163) {
		this.f163 = f163;
		empty = false;
	}

	public String getF164() {
		return f164;
	}

	public void setF164(String f164) {
		this.f164 = f164;
		empty = false;
	}

	public String getF165() {
		return f165;
	}

	public void setF165(String f165) {
		this.f165 = f165;
		empty = false;
	}

	public String getF166() {
		return f166;
	}

	public void setF166(String f166) {
		this.f166 = f166;
		empty = false;
	}

	public String getF167() {
		return f167;
	}

	public void setF167(String f167) {
		this.f167 = f167;
		empty = false;
	}

	public String getF168() {
		return f168;
	}

	public void setF168(String f168) {
		this.f168 = f168;
		empty = false;
	}

	public String getF169() {
		return f169;
	}

	public void setF169(String f169) {
		this.f169 = f169;
		empty = false;
	}

	public String getF170() {
		return f170;
	}

	public void setF170(String f170) {
		this.f170 = f170;
		empty = false;
	}

	public String getF171() {
		return f171;
	}

	public void setF171(String f171) {
		this.f171 = f171;
		empty = false;
	}

	public String getF172() {
		return f172;
	}

	public void setF172(String f172) {
		this.f172 = f172;
		empty = false;
	}

	public String getF173() {
		return f173;
	}

	public void setF173(String f173) {
		this.f173 = f173;
		empty = false;
	}

	public String getF174() {
		return f174;
	}

	public void setF174(String f174) {
		this.f174 = f174;
		empty = false;
	}

	public String getF175() {
		return f175;
	}

	public void setF175(String f175) {
		this.f175 = f175;
		empty = false;
	}

	public String getF176() {
		return f176;
	}

	public void setF176(String f176) {
		this.f176 = f176;
		empty = false;
	}

	public String getF177() {
		return f177;
	}

	public void setF177(String f177) {
		this.f177 = f177;
		empty = false;
	}

	public String getF178() {
		return f178;
	}

	public void setF178(String f178) {
		this.f178 = f178;
		empty = false;
	}

	public String getF179() {
		return f179;
	}

	public void setF179(String f179) {
		this.f179 = f179;
		empty = false;
	}

	public String getF180() {
		return f180;
	}

	public void setF180(String f180) {
		this.f180 = f180;
		empty = false;
	}

	public String getF181() {
		return f181;
	}

	public void setF181(String f181) {
		this.f181 = f181;
		empty = false;
	}

	public String getF182() {
		return f182;
	}

	public void setF182(String f182) {
		this.f182 = f182;
		empty = false;
	}

	public String getF183() {
		return f183;
	}

	public void setF183(String f183) {
		this.f183 = f183;
		empty = false;
	}

	public String getF184() {
		return f184;
	}

	public void setF184(String f184) {
		this.f184 = f184;
		empty = false;
	}

	public String getF185() {
		return f185;
	}

	public void setF185(String f185) {
		this.f185 = f185;
		empty = false;
	}

	public String getF186() {
		return f186;
	}

	public void setF186(String f186) {
		this.f186 = f186;
		empty = false;
	}

	public String getF187() {
		return f187;
	}

	public void setF187(String f187) {
		this.f187 = f187;
		empty = false;
	}

	public String getF188() {
		return f188;
	}

	public void setF188(String f188) {
		this.f188 = f188;
		empty = false;
	}

	public String getF189() {
		return f189;
	}

	public void setF189(String f189) {
		this.f189 = f189;
		empty = false;
	}

	public String getF190() {
		return f190;
	}

	public void setF190(String f190) {
		this.f190 = f190;
		empty = false;
	}

	public String getF191() {
		return f191;
	}

	public void setF191(String f191) {
		this.f191 = f191;
		empty = false;
	}

	public String getF192() {
		return f192;
	}

	public void setF192(String f192) {
		this.f192 = f192;
		empty = false;
	}

	public String getF193() {
		return f193;
	}

	public void setF193(String f193) {
		this.f193 = f193;
		empty = false;
	}

	public String getF194() {
		return f194;
	}

	public void setF194(String f194) {
		this.f194 = f194;
		empty = false;
	}

	public String getF195() {
		return f195;
	}

	public void setF195(String f195) {
		this.f195 = f195;
		empty = false;
	}

	public String getF196() {
		return f196;
	}

	public void setF196(String f196) {
		this.f196 = f196;
		empty = false;
	}

	public String getF197() {
		return f197;
	}

	public void setF197(String f197) {
		this.f197 = f197;
		empty = false;
	}

	public String getF198() {
		return f198;
	}

	public void setF198(String f198) {
		this.f198 = f198;
		empty = false;
	}

	public String getF199() {
		return f199;
	}

	public void setF199(String f199) {
		this.f199 = f199;
		empty = false;
	}

	public String getF200() {
		return f200;
	}

	public void setF200(String f200) {
		this.f200 = f200;
		empty = false;
	}

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}
}
