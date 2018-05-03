package com.winfo.szrsp.app.utils;


import java.util.Map;

public class SZMSAUtils {
    /**
     *
     * @param orgid 机构代码
     * @return 机构名称
     */
    public static String getOrgName(String orgid){
        if (orgid.equals("1401")) {
            return "大亚湾海事局";
        } else if (orgid.equals("1402")) {
            return "蛇口海事局";
        } else if (orgid.equals("1403")) {
            return "盐田海事局";
        } else if (orgid.equals("1404")) {
            return "宝安海事局";
        } else if (orgid.equals("1405")) {
            return "南山海事局";
        } else if (orgid.equals("1406")) {
            return "大铲海事局";
        } else {
            return "机构代码异常";
        }
    }
    //国籍代码
//    var NATIONCODE = map[string]string{
//        "003": "阿富汗",
//                "005": "阿尔巴尼亚",
//                "010": "阿尔及利亚",
//                "015": "安哥拉",
//                "020": "安圭拉",
//                "025": "荷属安的列斯",
//                "028": "安提瓜和巴布达",
//                "030": "阿根廷",
//                "031": "亚美尼亚",
//                "033": "阿鲁巴",
//                "035": "澳大利亚",
//                "040": "奥地利",
//                "043": "阿塞拜疆",
//                "045": "巴哈马",
//                "050": "巴林",
//                "055": "孟加拉国",
//                "060": "巴巴多斯",
//                "064": "白俄罗斯",
//                "065": "比利时",
//                "067": "伯利兹",
//                "070": "贝宁",
//                "075": "百慕大",
//                "080": "玻利维亚",
//                "082": "波斯尼亚与黑塞哥维那",
//                "085": "巴西",
//                "088": "文莱",
//                "090": "保加利亚",
//                "095": "缅甸",
//                "100": "喀麦隆",
//                "105": "加拿大",
//                "110": "佛得角",
//                "115": "开曼群岛",
//                "118": "运河岛",
//                "120": "智利",
//                "125": "中国",
//                "130": "哥伦比亚",
//                "135": "科摩罗",
//                "137": "库克群岛",
//                "140": "哥斯达黎加",
//                "144": "克罗地亚",
//                "145": "古巴",
//                "150": "塞浦路斯",
//                "155": "捷克",
//                "160": "柬埔寨",
//                "165": "丹麦",
//                "166": "吉布提",
//                "168": "多米尼克",
//                "170": "多米尼加共和国",
//                "175": "厄瓜多尔",
//                "180": "埃及",
//                "183": "萨尔瓦多",
//                "184": "爱沙尼亚",
//                "185": "埃塞俄比亚",
//                "186": "厄立特里亚",
//                "190": "赤道几内亚",
//                "195": "法罗群岛",
//                "198": "福克兰群岛",
//                "200": "斐济",
//                "205": "芬兰",
//                "210": "法国",
//                "212": "法属玻里尼西亚",
//                "215": "加蓬",
//                "220": "冈比亚",
//                "224": "乔治亚",
//                "230": "德国",
//                "235": "加纳",
//                "240": "直布罗陀",
//                "245": "希腊",
//                "250": "格林纳达",
//                "255": "危地马拉",
//                "260": "圭亚那",
//                "265": "法属圭亚那",
//                "267": "几内亚",
//                "269": "几内亚比绍",
//                "270": "洪都拉斯",
//                "271": "海地",
//                "275": "中国香港",
//                "280": "匈牙利",
//                "285": "冰岛",
//                "290": "印度",
//                "295": "印度尼西亚",
//                "300": "伊朗",
//                "305": "伊拉克",
//                "310": "爱尔兰",
//                "315": "以色列",
//                "320": "意大利",
//                "325": "科特迪瓦",
//                "330": "牙买加",
//                "335": "日本",
//                "340": "约旦",
//                "344": "哈萨克斯坦",
//                "345": "肯尼亚",
//                "350": "基里巴斯",
//                "355": "朝鲜",
//                "360": "韩国",
//                "365": "科威特",
//                "366": "吉尔吉斯斯坦",
//                "367": "拉脱维亚",
//                "368": "老挝",
//                "370": "黎巴嫩",
//                "375": "利比里亚",
//                "380": "利比亚",
//                "382": "立陶宛",
//                "383": "卢森堡",
//                "384": "中国澳门",
//                "385": "马达加斯加",
//                "390": "马来西亚",
//                "395": "马尔代夫",
//                "397": "马里",
//                "400": "马耳他",
//                "403": "马恩岛",
//                "404": "马绍尔群岛",
//                "405": "毛里塔尼亚",
//                "410": "毛里求斯",
//                "415": "墨西哥",
//                "417": "密克罗尼西亚",
//                "419": "摩尔多瓦",
//                "420": "摩纳哥",
//                "422": "蒙古",
//                "425": "蒙特塞拉特",
//                "430": "摩洛哥",
//                "435": "莫桑比克",
//                "439": "纳米比亚",
//                "440": "瑙鲁",
//                "445": "荷兰",
//                "450": "新西兰",
//                "455": "尼加拉瓜",
//                "460": "尼日尔",
//                "465": "尼日利亚",
//                "470": "挪威",
//                "475": "阿曼",
//                "480": "巴基斯坦",
//                "482": "帕劳群岛",
//                "485": "巴拿马",
//                "490": "巴布亚新几内亚",
//                "495": "巴拉圭",
//                "500": "秘鲁",
//                "505": "菲律宾",
//                "510": "波兰",
//                "520": "葡萄牙",
//                "525": "卡塔尔",
//                "530": "罗马尼亚",
//                "532": "俄罗斯",
//                "533": "圣海伦那",
//                "535": "圣卢西亚",
//                "540": "圣文森特和格林纳丁斯",
//                "541": "圣克里斯托弗和尼维斯联邦",
//                "545": "萨摩亚",
//                "550": "美属萨摩亚",
//                "552": "圣多美和普林西比",
//                "555": "沙特阿拉伯",
//                "560": "塞内加尔",
//                "565": "塞舌尔",
//                "567": "塞拉利昂",
//                "570": "新加坡",
//                "572": "斯洛伐克",
//                "573": "斯洛文尼亚",
//                "575": "所罗门群岛",
//                "580": "索马里",
//                "585": "南非",
//                "590": "西班牙",
//                "595": "斯里兰卡",
//                "600": "苏丹",
//                "605": "苏里南",
//                "610": "瑞典",
//                "615": "瑞士",
//                "620": "叙利亚",
//                "625": "中国台湾",
//                "626": "塔吉克斯坦",
//                "630": "坦桑尼亚",
//                "635": "泰国",
//                "640": "多哥",
//                "642": "汤加",
//                "645": "特立尼达和多巴哥",
//                "650": "突尼斯",
//                "655": "土耳其",
//                "656": "土库曼斯坦",
//                "660": "特克斯和凯科斯群岛",
//                "663": "图瓦卢",
//                "664": "乌克兰",
//                "666": "阿拉伯联合酋长国",
//                "667": "乌干达",
//                "670": "英国",
//                "675": "美国",
//                "680": "乌拉圭",
//                "682": "乌兹别克斯坦",
//                "685": "瓦努阿图",
//                "690": "委内瑞拉",
//                "695": "越南",
//                "707": "瓦利斯和富图纳群岛",
//                "710": "也门",
//                "725": "刚果民主共和国",
//                "750": "格鲁吉亚",
//                "899": "其他",
//                "908": "纽埃",
//    }

    public static String getCountryByCode(String code){
        switch (code){
            case "003":
                return "阿富汗";
            case "005":
                return "阿尔巴尼亚";
            case "010":
                return "安哥拉";
            case "020":
                return "安圭拉";
            case "025":
                return "荷属安的列斯";
            case "028":
                return "安提瓜和巴布达";
            case "030":
                return "阿根廷";
            case "031":
                return "亚美尼亚";
            case "033":
                return "阿鲁巴";
            case "035":
                return "澳大利亚";
            case "040":
                return "奥地利";
            case "043":
                return "阿塞拜疆";
            case "045":
                return "巴林";
            case "050":
                return "阿富汗";
            case "055":
                return "孟加拉国";
            case "060":
                return "巴巴多斯";
            case "064":
                return "白俄罗斯";
            case "065":
                return "比利时";
            case "067":
                return "伯利兹";
            case "070":
                return "贝宁";
            case "075":
                return "百慕大";
            case "080":
                return "玻利维亚";
            case "082":
                return "波斯尼亚与黑塞哥维那";
            case "085":
                return "巴西";
            case "088":
                return "文莱";
            case "090":
                return "保加利亚";
            case "095":
                return "缅甸";
            case "100":
                return "喀麦隆";
            case "105":
                return "加拿大";
            case "110":
                return "佛得角";
            case "115":
                return "开曼群岛";
            case "118":
                return "运河岛";
            case "120":
                return "智利";
            case "125":
                return "中国";
            case "130":
                return "哥伦比亚";
            case "135":
                return "科摩罗";
            case "137":
                return "库克群岛";
            case "140":
                return "哥斯达黎加";
            case "144":
                return "克罗地亚";
            case "145":
                return "古巴";
            case "150":
                return "塞浦路斯";
            case "155":
                return "捷克";
            case "160":
                return "柬埔寨";
            case "165":
                return "丹麦";
            case "166":
                return "吉布提";
            case "168":
                return "多米尼克";
            case "170":
                return "多米尼加共和国";
            case "175":
                return "厄瓜多尔";
            case "180":
                return "埃及";
            case "183":
                return "萨尔瓦多";
            case "184":
                return "爱沙尼亚";
            case "185":
                return "埃塞俄比亚";
            case "186":
                return "厄立特里亚";
            case "190":
                return "赤道几内亚";
            case "195":
                return "法罗群岛";
            case "198":
                return "福克兰群岛";
            case "200":
                return "斐济";
            case "205":
                return "芬兰";
            case "210":
                return "法国";
            case "212":
                return "法属玻里尼西亚";
            case "215":
                return "加蓬";
            case "220":
                return "冈比亚";
            case "224":
                return "乔治亚";
            case "230":
                return "德国";
            case "235":
                return "加纳";
            case "240":
                return "直布罗陀";
            case "245":
                return "希腊";
            case "250":
                return "格林纳达";
            case "255":
                return "危地马拉";
            case "260":
                return "圭亚那";
            case "265":
                return "法属圭亚那";
            case "267":
                return "几内亚";
            case "269":
                return "几内亚比绍";
            case "270":
                return "洪都拉斯";
            case "271":
                return "海地";
            case "275":
                return "中国香港";
            case "280":
                return "匈牙利";
            case "285":
                return "冰岛";
            case "290":
                return "印度";
            case "295":
                return "印度尼西亚";
            case "300":
                return "伊朗";
            case "305":
                return "伊拉克";
            case "310":
                return "爱尔兰";
            case "315":
                return "以色列";
            case "320":
                return "意大利";
            case "325":
                return "科特迪瓦";
            case "330":
                return "牙买加";
            case "335":
                return "日本";
            case "340":
                return "约旦";
            case "344":
                return "哈萨克斯坦";
            case "345":
                return "肯尼亚";
            case "350":
                return "基里巴斯";
            case "355":
                return "朝鲜";
            case "360":
                return "韩国";
            case "365":
                return "科威特";
            case "366":
                return "吉尔吉斯斯坦";
            case "367":
                return "拉脱维亚";
            case "368":
                return "老挝";
            case "370":
                return "黎巴嫩";
            case "375":
                return "利比里亚";
            case "380":
                return "利比亚";
            case "382":
                return "立陶宛";
            case "383":
                return "卢森堡";
            case "384":
                return "中国澳门";
            case "385":
                return "马达加斯加";
            case "390":
                return "马来西亚";
            case "395":
                return "马尔代夫";
            case "397":
                return "马里";
            case "400":
                return "马耳他";
            case "403":
                return "马恩岛";
            case "404":
                return "马绍尔群岛";
            case "405":
                return "毛里塔尼亚";
            case "410":
                return "毛里求斯";
            case "415":
                return "墨西哥";
            case "417":
                return "密克罗尼西亚";
            case "419":
                return "摩尔多瓦";
            case "420":
                return "摩纳哥";
            case "422":
                return "蒙古";
            case "425":
                return "蒙特塞拉特";
            case "430":
                return "摩洛哥";
            case "435":
                return "莫桑比克";
            case "439":
                return "纳米比亚";
            case "440":
                return "瑙鲁";
            case "445":
                return "荷兰";
            case "450":
                return "新西兰";
            case "455":
                return "尼加拉瓜";
            case "460":
                return "尼日尔";
            case "465":
                return "尼日利亚";
            case "470":
                return "挪威";
            case "475":
                return "阿曼";
            case "480":
                return "巴基斯坦";
            case "482":
                return "帕劳群岛";
            case "485":
                return "巴拿马";
            case "490":
                return "巴布亚新几内亚";
            case "495":
                return "巴拉圭";
            case "500":
                return "秘鲁";
            case "505":
                return "菲律宾";
            case "510":
                return "波兰";
            case "520":
                return "葡萄牙";
            case "525":
                return "卡塔尔";
            case "530":
                return "罗马尼亚";
            case "532":
                return "俄罗斯";
            case "533":
                return "圣海伦那";
            case "535":
                return "圣卢西亚";
            case "540":
                return "圣文森特和格林纳丁斯";
            case "541":
                return "圣克里斯托弗和尼维斯联邦";
            case "545":
                return "萨摩亚";
            case "550":
                return "美属萨摩亚";
            case "552":
                return "圣多美和普林西比";
            case "555":
                return "沙特阿拉伯";
            case "560":
                return "塞内加尔";
            case "565":
                return "塞舌尔";
            case "567":
                return "塞拉利昂";
            case "570":
                return "新加坡";
            case "572":
                return "斯洛伐克";
            case "573":
                return "斯洛文尼亚";
            case "575":
                return "所罗门群岛";

            case "580":
                return "索马里";
            case "585":
                return "南非";
            case "590":
                return "西班牙";
            case "595":
                return "斯里兰卡";
            case "600":
                return "苏丹";
            case "605":
                return "苏里南";
            case "610":
                return "瑞典";
            case "615":
                return "瑞士";
            case "620":
                return "叙利亚";
            case "625":
                return "中国台湾";
            case "626":
                return "塔吉克斯坦";
            case "630":
                return "坦桑尼亚";
            case "635":
                return "泰国";
            case "640":
                return "多哥";
            case "642":
                return "汤加";
            case "645":
                return "特立尼达和多巴哥";
            case "650":
                return "突尼斯";
            case "655":
                return "土耳其";
            case "656":
                return "土库曼斯坦";
            case "660":
                return "特克斯和凯科斯群岛";
            case "663":
                return "图瓦卢";
            case "664":
                return "乌克兰";
            case "666":
                return "阿拉伯联合酋长国";
            case "667":
                return "乌干达";
            case "670":
                return "英国";
            case "675":
                return "美国";
            case "680":
                return "乌拉圭";
            case "682":
                return "乌兹别克斯坦";
            case "685":
                return "瓦努阿图";
            case "690":
                return "委内瑞拉";
            case "695":
                return "越南";
            case "707":
                return "瓦利斯和富图纳群岛";
            case "710":
                return "也门";
            case "725":
                return "刚果民主共和国";
            case "750":
                return "格鲁吉亚";
            case "899":
                return "其他";
            case "908":
                return "纽埃";
            default:
                return "其他";
        }

    }
}