package com.winfo.dnc.sdk.utils;

/**
 * Created by winfo053 on 2018/3/23.
 */

public class AISUtils {

    public static final int AIS_KE=1;//客船
    public static final int AIS_PUTONGHUO=2;// 普通货船
    public static final int AIS_YEHUO=3;//液货船
    public static final int AIS_GONGCHENG=4;//工程船
    public static final int AIS_GONGZUO=5;//工作船
    public static final int AIS_TUO=6;//拖船
    public static final int AIS_YU=7;//渔船
    public static final int AIS_QITA=8;//其他船
    public static int getShipType(String SZCLX){
        switch (SZCLX){
            //客船
            case "0100":
                return AIS_KE ;
            case "0101":
                return AIS_KE ;
            case "0102":
                return AIS_KE ;
            case "0103":
                return AIS_KE ;
            case "0104":
                return AIS_KE ;
            case "0105":
                return AIS_KE ;
            case "0106":
                return AIS_KE ;
            case "0107":
                return AIS_KE ;
            case "0108":
                return AIS_KE ;
            case "0109":
                return AIS_KE ;
            case "0110":
                return AIS_KE ;
            case "0111":
                return AIS_KE ;
            case "0112":
                return AIS_KE ;
            //普通货船
            case "0200":
                return AIS_PUTONGHUO ;
            case "0201":
                return AIS_PUTONGHUO ;
            case "0202":
                return AIS_PUTONGHUO ;
            case "0203":
                return AIS_PUTONGHUO ;
            case "0204":
                return AIS_PUTONGHUO ;
            case "0205":
                return AIS_PUTONGHUO ;
            case "0206":
                return AIS_PUTONGHUO ;
            case "0207":
                return AIS_PUTONGHUO ;
            case "0208":
                return AIS_PUTONGHUO ;
            case "0209":
                return AIS_PUTONGHUO ;
            case "0210":
                return AIS_PUTONGHUO ;
            case "0211":
                return AIS_PUTONGHUO ;
            case "0212":
                return AIS_PUTONGHUO ;
            case "0213":
                return AIS_PUTONGHUO ;
            case "0214":
                return AIS_PUTONGHUO ;
            case "0215":
                return AIS_PUTONGHUO ;
            case "0216":
                return AIS_PUTONGHUO ;
            case "0217":
                return AIS_PUTONGHUO ;
            case "0218":
                return AIS_PUTONGHUO ;

            //液货船
            case "0300":
                return AIS_YEHUO;
            case "0301":
                return AIS_YEHUO ;
            case "0302":
                return AIS_YEHUO ;
            case "0303":
                return AIS_YEHUO ;
            case "0304":
                return AIS_YEHUO ;
            case "0305":
                return AIS_YEHUO ;
            case "0306":
                return AIS_YEHUO ;
            case "0307":
                return AIS_YEHUO ;


                //工程船类
            case "0400":
                return AIS_GONGCHENG ;
            case "0401":
                return AIS_GONGCHENG ;
            case "0402":
                return AIS_GONGCHENG ;
            case "0403":
                return AIS_GONGCHENG ;
            case "0404":
                return AIS_GONGCHENG ;
            case "0405":
                return AIS_GONGCHENG ;
            case "0406":
                return AIS_GONGCHENG ;
            case "0407":
                return AIS_GONGCHENG ;
            case "0408":
                return AIS_GONGCHENG ;
            case "0409":
                return AIS_GONGCHENG ;
            case "0410":
                return AIS_GONGCHENG ;
            case "0411":
                return AIS_GONGCHENG ;
            case "0412":
                return AIS_GONGCHENG ;
            case "0413":
                return AIS_GONGCHENG ;
            case "0414":
                return AIS_GONGCHENG ;

            //工作船类
            case "0500":
                return AIS_GONGZUO ;
            case "0501":
                return AIS_GONGZUO ;
            case "0502":
                return AIS_GONGZUO ;
            case "0503":
                return AIS_GONGZUO ;
            case "0504":
                return AIS_GONGZUO ;
            case "0505":
                return AIS_GONGZUO ;
            case "0506":
                return AIS_GONGZUO ;
            //拖船类
            case "0600":
                return AIS_TUO;
            case "0601":
                return AIS_TUO ;
            case "0602":
                return AIS_TUO ;
                //yu渔船
            case "30":
                return AIS_YU ;

               //其他船
            case "0900":
                return AIS_QITA ;
            case "0901":
                return AIS_QITA ;
            case "0902":
                return AIS_QITA ;
            case "0903":
                return AIS_QITA ;
            case "0904":
                return AIS_QITA ;
            case "0905":
                return AIS_QITA ;
            case "0906":
                return AIS_QITA ;
            case "0907":
                return AIS_QITA ;
            case "0908":
                return AIS_QITA ;
            case "0909":
                return AIS_QITA ;
            case "0910":
                return AIS_QITA ;
            case "0911":
                return AIS_QITA ;
            case "0912":
                return AIS_QITA ;
            case "0913":
                return AIS_QITA ;
            case "0914":
                return AIS_QITA ;

            default:
                return AIS_QITA;
        }
    }
}
