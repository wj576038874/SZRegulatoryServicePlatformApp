package com.winfo.dnc.sdk;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

class BasicAlgorithm {
	 
		float bitmapScale=1.0f;
		private  int TileSize = 256;
		private MapViewInfo mapViewInfo;
		private Bitmap loadingBitmap;//默认地图加载过程中或者加载失败的图片
	    private double WebMecartoSpanPerPixel = Math.PI * 6378137 / 256;
	    private WinfoDNCParams winfoDNCParams;
	
		BasicAlgorithm(WinfoDNCParams winfoDNCParams, Bitmap loadingBitmap) {
			this.winfoDNCParams = winfoDNCParams;
			this.loadingBitmap = loadingBitmap;
			this.mapViewInfo=new MapViewInfo();
			this.TileSize=(int) (bitmapScale*256);
//			this.bitmapScale=this.TileSize/256;
			this.WebMecartoSpanPerPixel= Math.PI * 6378137/this.TileSize;
		}

		
		void chengae(float scale){
			this.bitmapScale = bitmapScale*scale;
			if(bitmapScale <= 1.0f){
				bitmapScale = 1.5f;
				if(winfoDNCParams.level > winfoDNCParams.minLevel){
					winfoDNCParams.level = winfoDNCParams.level - 1;
				}
			}else if(bitmapScale >= 2.0f){
				bitmapScale = 1.5f;
				if(winfoDNCParams.level < winfoDNCParams.maxLevel ){
					winfoDNCParams.level = winfoDNCParams.level + 1;
				}
			}
			this.TileSize=(int) (bitmapScale*256);
			this.WebMecartoSpanPerPixel= Math.PI * 6378137/this.TileSize;
		}
		private double[] Scales = {
				        1479146777.27283,
	                    739573388.636414,
	                    369786694.318207,
	                    184893347.159103,
	                    92446673.5795514,
	                    46223336.7897757,
	                    23111668.3948879,
	                    11555834.1974437,
	                    5777917.09872183,
	                    2888958.54936091,
	                    1444479.27468101,
	                    722239.637340778,
	                    361119.818669839,
	                    180559.90933492,
	                    90279.9546669098,
	                    45139.9773337299,
	                    22569.9886668649,
	                    11284.9943334325};
		/**
		 * 
		 * @param layer
		 * @param width
		 * @param height
		 * @param lon
		 * @param lat
		 * @param level
		 * @return
		 */
		MapViewInfo calculateViewInfo(int layer, double width, double height, double lon, double lat, int level) {
	  
	    	ViewPortParam vp = calculaueViewportParam(width, height, lon, lat, level);
	    	mapViewInfo= getTileInfoByViewportParam(vp);
	    	mapViewInfo.Scale = vp.Scale;
	    	mapViewInfo.Mode = layer;
	    	mapViewInfo.Layer = layer;
	    	mapViewInfo.CenterX = lon;
	    	mapViewInfo.CenterY = lat;
	    	mapViewInfo.Width = width;
	    	mapViewInfo.Height = height;
	    	mapViewInfo.SpanPerPixel = vp.SpanPerPixel;
	    	mapViewInfo.MinX = vp.MinX;
	    	mapViewInfo.MinY = vp.MinY;
	        String code;
	        switch (layer) {
	            case 0:
	                code = "3EA2FC";
	                break;
	            case 1:
	                code = "3EA27C";
	                break;
	            case 2:
	                code = "3EA2BC";
	                break;
	            case 3:
	                code = "3EA23C";
	                break;
	            case 4:
	                code = "3EA2DC";
	                break;
	            case 5:
	                code = "3EA23C";
	                break;
	            default:
	                code = "3EA2FC";
	                break;
	        }
	        mapViewInfo.Code = code;
	        mapViewInfo.vp=vp;
	        return mapViewInfo;
	    }
	    /**
	     * 
	     * @param vp
	     * @return
	     */
		private MapViewInfo getTileInfoByViewportParam(ViewPortParam vp) {
	    	
	    	Box Box = new Box();
	        int  row1, row2, col1, col2;
	        double L,T;
	        List<TileInfo> tileInfoList=new ArrayList<>();
	        Point LT = screenPoint2Coordinate(vp, 0, 0);
	        Box.T = LT.lat;
	        Box.L = LT.lon;
	        Point RB = screenPoint2Coordinate(vp, vp.Width, vp.Height);
	        Box.B = RB.lat;
	        Box.R = RB.lon;
	        List<TileInfo> rc = calculateTile(Box, vp.Level);
	        row1 = rc.get(0).Y;
	        row2 = rc.get(1).Y; 
	        col1 = rc.get(0).X;
	        col2 = rc.get(1).X;
	        com.winfo.dnc.sdk.Box box = calculateTileExtend(col1,row1,vp.Level);
	        L = box.L;
	        T = box.T;
	        Point Offset = coordinate2ScreenPoint(vp, L, T);
	        vp.OffsetX = Offset.X;
	        vp.OffsetY = Offset.Y;
	        mapViewInfo.Level = vp.Level;
	        mapViewInfo.Box = Box;

	        double offsety = vp.OffsetY;
	        double offsetx;
	        for (int y = row1; y <= row2; y++) {
	            offsetx = vp.OffsetX;
	            for (int x = col1; x <= col2; x++) {
	            	TileInfo tileInfo = new TileInfo();
	                tileInfo.Y = y;
	                tileInfo.X = x;
	                tileInfo.Height = TileSize;
	                tileInfo.Width = TileSize;
	                tileInfo.OffSetX = offsetx;
	                tileInfo.OffSetY = offsety;
	                tileInfo.bitmap = loadingBitmap;
	                offsetx += TileSize;
	                tileInfoList.add(tileInfo);
	            }
	            offsety += TileSize;
	        }
	        mapViewInfo.TileInfoList=tileInfoList;
	        return mapViewInfo;
	    }
	    /**
	     * 
	     * @param width
	     * @param height
	     * @param lon
	     * @param lat
	     * @param level
	     * @return
	     */
		private ViewPortParam calculaueViewportParam(double width, double height, double lon, double lat, int level) {
	        double dx, dy;
	        ViewPortParam Result = new ViewPortParam();

	        Result.Width = width;
	        Result.Height = height;
	        Result.CenterLon = lon;
	        Result.CenterLat = lat;
	        Result.Level = level;
	        Point ll = mercatorBLtoXY(lon, lat);
	        Result.CenterX = ll.X;
	        Result.CenterY = ll.Y;
	        Result.SpanPerPixel = WebMecartoSpanPerPixel / Math.pow(2, level - 1);
	        dx = width * 0.5 * Result.SpanPerPixel;
	        dy = height * 0.5 * Result.SpanPerPixel;
	        Result.MinX = Result.CenterX - dx;
	        Result.MaxX = Result.CenterX + dx;
	        Result.MaxY = Result.CenterY + dy;
	        Result.MinY = Result.CenterY - dy;
	        Result.Scale =Scales[level-1];   
	        return Result;
	        

	    }
	    /**
	     * ī��������
	     * @param lon
	     * @param lat
	     * @return
	     */
		private static Point mercatorBLtoXY(double lon, double lat) {
			Point p = new Point();
			p.X = lon * 6378137.0 * Math.PI / 180.0;
			double T = Math.tan((90.0 + lat) * Math.PI / 360.0);
			if (Math.abs(T - 1) < 0.000000000001) {
				T = 1.0;
			}
			p.Y = Math.log(T) * 6378137.0;
			return p;
		}
		/***
		 * 
		 * @param X
		 * @param Y
		 * @return
		 */
		private static Point mercatorXYtoBL(double X, double Y) {
			Point p = new Point();
			p.lon = X * 180 / Math.PI / 6378137.0;
			p.lat = Y * 180 / Math.PI / 6378137.0;
			p.lat = 180/ Math.PI* (2 * Math.atan(Math.exp(p.lat * Math.PI / 180.0)) - Math.PI / 2.0);
			return p;
		}
		
		/***
		 * 
		 * @param vp
		 * @param x
		 * @param y
		 * @return
		 */
		Point screenPoint2Coordinate(ViewPortParam vp, double x, double y) {
	        double Longitude = vp.MinX + x * vp.SpanPerPixel;
	        double Latitude = (vp.Height - y) * vp.SpanPerPixel + vp.MinY;
			return mercatorXYtoBL(Longitude,Latitude);

	    }

	    /**
	     * 
	     * @param vp
	     * @param lon
	     * @param lat
	     * @return
	     */
		Point coordinate2ScreenPoint(ViewPortParam vp, double lon, double lat) {
	        Point XY = mercatorBLtoXY(lon,lat);
	        double X = Math.round((XY.X - vp.MinX) / vp.SpanPerPixel);
	        double Y = (vp.Height - Math.round((XY.Y - vp.MinY) / vp.SpanPerPixel));
	        Point p=new Point();
	        p.X=X;
	        p.Y=Y;
	        return p;
	    }

//	    /**
//	     *
//	     * @param Level
//	     * @param row
//	     * @param col
//	     * @return
//	     */
//	    public int calculateTileSize(int Level, double row, double col) {
//
//	    	double T,L,B,R;
//	        Box box= calculateTileExtend(col, row, Level);
//
//	        Point TL = mercatorBLtoXY(box.T, box.L);
//	        T =TL.X;
//	        L = TL.Y;
//	        Point BR = mercatorBLtoXY(box.B, box.R);
//	        B = BR.X;
//	        R =BR.Y;
//	        int Result = 0;
//	        if (L == R) {
//	            Result = 0;
//	        }
//	        else {
//	            Result = (int) Math.round(TileSize * Math.abs((B - T) / (R - L)));
//	        }
//	        return Result;
//	    };

	    /**
	     * 
	     * @param x
	     * @param y
	     * @param z
	     * @return
	     */
		private Box calculateTileExtend(double x, double y, int z){
	    	Box box = new Box();
	    	double cn = Math.PI - (2.0 * Math.PI * y) / Math.pow(2, z);
	        box.L = x / Math.pow(2, z) * 360.0 - 180.0;
	        box.T = 180.0 / Math.PI * Math.atan(Math.sinh(cn));
	    	
	    	cn = Math.PI - (2.0 * Math.PI * (y+1)) / Math.pow(2, z);
	        box.R = (x+1) / Math.pow(2, z) * 360.0 - 180.0;
	        box.B = 180.0 / Math.PI * Math.atan(Math.sinh(cn));
	    	return box;
	    }

	    /***
	     * 
	     * @param box
	     * @param level
	     * @return
	     */
		private List<TileInfo> calculateTile(Box box, int level) {

	    	List<TileInfo> tileInfoList=new ArrayList<>();
	    	TileInfo r1 = calculateTileByCoordinate(box.L, box.T, level);
	    	TileInfo r2 = calculateTileByCoordinate(box.R, box.B, level);
	    	tileInfoList.add(r1);
	    	tileInfoList.add(r2);
	    	
	        return tileInfoList;
	    }
	    /***
	     * 
	     * @param lon
	     * @param lat
	     * @param level
	     * @return
	     */
		private TileInfo calculateTileByCoordinate(double lon, double lat, int level) {
	    	TileInfo tile = new TileInfo();
	          tile.X  =  (int) Math.floor((lon + 180.0) / 360.0 * Math.pow(2,level));
	          tile.Y =  (int) Math.floor((1.0 - Math.log(Math.tan(lat * Math.PI / 180.0) + 1.0 / Math.cos(lat * Math.PI / 180.0)) /  Math.PI) * 0.5 * Math.pow(2,level));
	          tile.Z = level;
	          return tile;

	    }

}
