package com.winfo.szrsp.app.sdk.entity.shipdata;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HoBo on 2018/4/12.
 */

public class ShipXianChangInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * siteSupervisionDetail : [{"comment_code":"","comment_desc":"","content_code":"1101","content_desc":"中国籍船舶是否开展开航前自查:","correct_mark":"0","create_time":"2018-03-19 00:00:00","creator_code":"360502198810147177","description":"","if_problem":"0","initial_inspect_no":"","inspect_no":"20180319120818377140300","result":"1","seq_no":1},{"comment_code":"","comment_desc":"","content_code":"1102","content_desc":"法定证书文书配备及记录是否齐全:","correct_mark":"0","create_time":"2018-03-19 00:00:00","creator_code":"360502198810147177","description":"","if_problem":"0","initial_inspect_no":"","inspect_no":"20180319120818377140300","result":"1","seq_no":2},{"comment_code":"","comment_desc":"","content_code":"1103","content_desc":"船员配备是否满足要求:","correct_mark":"0","create_time":"2018-03-19 00:00:00","creator_code":"360502198810147177","description":"","if_problem":"0","initial_inspect_no":"","inspect_no":"20180319120818377140300","result":"1","seq_no":3},{"comment_code":"","comment_desc":"","content_code":"1104","content_desc":"客货载运及货物系固绑扎是否符合规定:","correct_mark":"0","create_time":"2018-03-19 00:00:00","creator_code":"360502198810147177","description":"","if_problem":"0","initial_inspect_no":"","inspect_no":"20180319120818377140300","result":"2","seq_no":4},{"comment_code":"","comment_desc":"","content_code":"1105","content_desc":"船舶防污染措施是否落实:","correct_mark":"0","create_time":"2018-03-19 00:00:00","creator_code":"360502198810147177","description":"","if_problem":"0","initial_inspect_no":"","inspect_no":"20180319120818377140300","result":"1","seq_no":5},{"comment_code":"","comment_desc":"","content_code":"1106","content_desc":"船舶航行、停泊、作业是否符合规定:","correct_mark":"0","create_time":"2018-03-19 00:00:00","creator_code":"360502198810147177","description":"","if_problem":"0","initial_inspect_no":"","inspect_no":"20180319120818377140300","result":"1","seq_no":6},{"comment_code":"","comment_desc":"","content_code":"1107","content_desc":"船舶是否按要求进行进出港报告或者办理进出港手续:","correct_mark":"0","create_time":"2018-03-19 00:00:00","creator_code":"360502198810147177","description":"","if_problem":"0","initial_inspect_no":"","inspect_no":"20180319120818377140300","result":"1","seq_no":7},{"comment_code":"","comment_desc":"","content_code":"1108","content_desc":"船舶是否按照相关规定缴纳相关费税:","correct_mark":"0","create_time":"2018-03-19 00:00:00","creator_code":"360502198810147177","description":"","if_problem":"0","initial_inspect_no":"","inspect_no":"20180319120818377140300","result":"2","seq_no":8},{"comment_code":"","comment_desc":"","content_code":"1101","content_desc":"中国籍船舶是否开展开航前自查:","correct_mark":"0","create_time":"2018-03-26 00:00:00","creator_code":"360481198307090417","description":"","if_problem":"0","initial_inspect_no":"","inspect_no":"20180326143825426140300","result":"1","seq_no":1},{"comment_code":"","comment_desc":"","content_code":"1102","content_desc":"法定证书文书配备及记录是否齐全:","correct_mark":"0","create_time":"2018-03-26 00:00:00","creator_code":"360481198307090417","description":"","if_problem":"0","initial_inspect_no":"","inspect_no":"20180326143825426140300","result":"1","seq_no":2},{"comment_code":"","comment_desc":"","content_code":"1103","content_desc":"船员配备是否满足要求:","correct_mark":"0","create_time":"2018-03-26 00:00:00","creator_code":"360481198307090417","description":"","if_problem":"0","initial_inspect_no":"","inspect_no":"20180326143825426140300","result":"1","seq_no":3},{"comment_code":"","comment_desc":"","content_code":"1105","content_desc":"船舶防污染措施是否落实:","correct_mark":"0","create_time":"2018-03-26 00:00:00","creator_code":"360481198307090417","description":"","if_problem":"0","initial_inspect_no":"","inspect_no":"20180326143825426140300","result":"1","seq_no":4},{"comment_code":"","comment_desc":"","content_code":"1106","content_desc":"船舶航行、停泊、作业是否符合规定:","correct_mark":"0","create_time":"2018-03-26 00:00:00","creator_code":"360481198307090417","description":"","if_problem":"0","initial_inspect_no":"","inspect_no":"20180326143825426140300","result":"1","seq_no":5},{"comment_code":"","comment_desc":"","content_code":"1107","content_desc":"船舶是否按要求进行进出港报告或者办理进出港手续:","correct_mark":"0","create_time":"2018-03-26 00:00:00","creator_code":"360481198307090417","description":"","if_problem":"0","initial_inspect_no":"","inspect_no":"20180326143825426140300","result":"1","seq_no":6}]
     * siteSupervisionReport : null
     * siteSupervisionReportList : [{"berth_code":"1404013","busi_no":"20180326143825587140300","captain_name":"","correct_mark":"0","defect_num":6,"if_problem":"0","if_safeinspect":"0","initial_inspect_mark":"0","initial_inspect_no":"","inspect_date":"2018-03-26 00:00:00","inspect_no":"20180326143825426140300","inspect_org":"140300","inspect_time_limit":"1","inspect_type":"","inspector_code":"1405140514,1404150415","inspector_name":"李玉东,邓彬见","is_special_inspect":"0","org_code":"140300","pratice_inspector_code":"","pratice_inspector_name":"","priority_order":"SP3","reason":"","remark":"3#锚地","risk_attribute":"S2","ship_id":"CN20118764119","ship_manager":"","ship_manager_addr":"","ship_manager_fax":"","ship_name_cn":"江仁03","ship_no":"A140012000008","ship_operator":"深圳市珠江口嘉仁溢油应急服务有限公司","ship_operator_addr":"","ship_operator_fax":"","spot_busi_no":"20180326143825750140000","task_id":"S05920180326143825683140300","unclosed_num":6,"voyage_id":""},{"berth_code":"1400112","busi_no":"20180319120818490140300","captain_name":"","correct_mark":"0","defect_num":8,"if_problem":"0","if_safeinspect":"0","initial_inspect_mark":"0","initial_inspect_no":"","inspect_date":"2018-03-19 00:00:00","inspect_no":"20180319120818377140300","inspect_org":"140300","inspect_time_limit":"1","inspect_type":"2018中小型船舶专项整治行动","inspector_code":"1404540454,1404920492","inspector_name":"彭勇平,王新波","is_special_inspect":"1","org_code":"140300","pratice_inspector_code":"","pratice_inspector_name":"","priority_order":"SP1","reason":"","remark":"","risk_attribute":"S2","ship_id":"CN20118764119","ship_manager":"","ship_manager_addr":"","ship_manager_fax":"","ship_name_cn":"江仁03","ship_no":"A140012000008","ship_operator":"深圳市珠江口嘉仁溢油应急服务有限公司","ship_operator_addr":"","ship_operator_fax":"","spot_busi_no":"20180319120818587140000","task_id":"S05920180319120818551140300","unclosed_num":8,"voyage_id":""}]
     */

    private Object siteSupervisionReport;
    private List<SiteSupervisionDetailBean> siteSupervisionDetail;
    private List<SiteSupervisionReportListBean> siteSupervisionReportList;

    public Object getSiteSupervisionReport() {
        return siteSupervisionReport;
    }

    public void setSiteSupervisionReport(Object siteSupervisionReport) {
        this.siteSupervisionReport = siteSupervisionReport;
    }

    public List<SiteSupervisionDetailBean> getSiteSupervisionDetail() {
        return siteSupervisionDetail;
    }

    public void setSiteSupervisionDetail(List<SiteSupervisionDetailBean> siteSupervisionDetail) {
        this.siteSupervisionDetail = siteSupervisionDetail;
    }

    public List<SiteSupervisionReportListBean> getSiteSupervisionReportList() {
        return siteSupervisionReportList;
    }

    public void setSiteSupervisionReportList(List<SiteSupervisionReportListBean> siteSupervisionReportList) {
        this.siteSupervisionReportList = siteSupervisionReportList;
    }

    public static class SiteSupervisionDetailBean implements Serializable{
        /**
         * comment_code :
         * comment_desc :
         * content_code : 1101
         * content_desc : 中国籍船舶是否开展开航前自查:
         * correct_mark : 0
         * create_time : 2018-03-19 00:00:00
         * creator_code : 360502198810147177
         * description :
         * if_problem : 0
         * initial_inspect_no :
         * inspect_no : 20180319120818377140300
         * result : 1
         * seq_no : 1
         */

        private String comment_code;
        private String comment_desc;
        private String content_code;
        private String content_desc;
        private String correct_mark;
        private String create_time;
        private String creator_code;
        private String description;
        private String if_problem;
        private String initial_inspect_no;
        private String inspect_no;
        private String result;
        private int seq_no;

        public String getComment_code() {
            return comment_code;
        }

        public void setComment_code(String comment_code) {
            this.comment_code = comment_code;
        }

        public String getComment_desc() {
            return comment_desc;
        }

        public void setComment_desc(String comment_desc) {
            this.comment_desc = comment_desc;
        }

        public String getContent_code() {
            return content_code;
        }

        public void setContent_code(String content_code) {
            this.content_code = content_code;
        }

        public String getContent_desc() {
            return content_desc;
        }

        public void setContent_desc(String content_desc) {
            this.content_desc = content_desc;
        }

        public String getCorrect_mark() {
            return correct_mark;
        }

        public void setCorrect_mark(String correct_mark) {
            this.correct_mark = correct_mark;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getCreator_code() {
            return creator_code;
        }

        public void setCreator_code(String creator_code) {
            this.creator_code = creator_code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIf_problem() {
            return if_problem;
        }

        public void setIf_problem(String if_problem) {
            this.if_problem = if_problem;
        }

        public String getInitial_inspect_no() {
            return initial_inspect_no;
        }

        public void setInitial_inspect_no(String initial_inspect_no) {
            this.initial_inspect_no = initial_inspect_no;
        }

        public String getInspect_no() {
            return inspect_no;
        }

        public void setInspect_no(String inspect_no) {
            this.inspect_no = inspect_no;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public int getSeq_no() {
            return seq_no;
        }

        public void setSeq_no(int seq_no) {
            this.seq_no = seq_no;
        }
    }

    public static class SiteSupervisionReportListBean implements Serializable{
        /**
         * berth_code : 1404013
         * busi_no : 20180326143825587140300
         * captain_name :
         * correct_mark : 0
         * defect_num : 6
         * if_problem : 0
         * if_safeinspect : 0
         * initial_inspect_mark : 0
         * initial_inspect_no :
         * inspect_date : 2018-03-26 00:00:00
         * inspect_no : 20180326143825426140300
         * inspect_org : 140300
         * inspect_time_limit : 1
         * inspect_type :
         * inspector_code : 1405140514,1404150415
         * inspector_name : 李玉东,邓彬见
         * is_special_inspect : 0
         * org_code : 140300
         * pratice_inspector_code :
         * pratice_inspector_name :
         * priority_order : SP3
         * reason :
         * remark : 3#锚地
         * risk_attribute : S2
         * ship_id : CN20118764119
         * ship_manager :
         * ship_manager_addr :
         * ship_manager_fax :
         * ship_name_cn : 江仁03
         * ship_no : A140012000008
         * ship_operator : 深圳市珠江口嘉仁溢油应急服务有限公司
         * ship_operator_addr :
         * ship_operator_fax :
         * spot_busi_no : 20180326143825750140000
         * task_id : S05920180326143825683140300
         * unclosed_num : 6
         * voyage_id :
         */

        private String berth_code;
        private String busi_no;
        private String captain_name;
        private String correct_mark;
        private int defect_num;
        private String if_problem;
        private String if_safeinspect;
        private String initial_inspect_mark;
        private String initial_inspect_no;
        private String inspect_date;
        private String inspect_no;
        private String inspect_org;
        private String inspect_time_limit;
        private String inspect_type;
        private String inspector_code;
        private String inspector_name;
        private String is_special_inspect;
        private String org_code;
        private String pratice_inspector_code;
        private String pratice_inspector_name;
        private String priority_order;
        private String reason;
        private String remark;
        private String risk_attribute;
        private String ship_id;
        private String ship_manager;
        private String ship_manager_addr;
        private String ship_manager_fax;
        private String ship_name_cn;
        private String ship_no;
        private String ship_operator;
        private String ship_operator_addr;
        private String ship_operator_fax;
        private String spot_busi_no;
        private String task_id;
        private int unclosed_num;
        private String voyage_id;

        public String getBerth_code() {
            return berth_code;
        }

        public void setBerth_code(String berth_code) {
            this.berth_code = berth_code;
        }

        public String getBusi_no() {
            return busi_no;
        }

        public void setBusi_no(String busi_no) {
            this.busi_no = busi_no;
        }

        public String getCaptain_name() {
            return captain_name;
        }

        public void setCaptain_name(String captain_name) {
            this.captain_name = captain_name;
        }

        public String getCorrect_mark() {
            return correct_mark;
        }

        public void setCorrect_mark(String correct_mark) {
            this.correct_mark = correct_mark;
        }

        public int getDefect_num() {
            return defect_num;
        }

        public void setDefect_num(int defect_num) {
            this.defect_num = defect_num;
        }

        public String getIf_problem() {
            return if_problem;
        }

        public void setIf_problem(String if_problem) {
            this.if_problem = if_problem;
        }

        public String getIf_safeinspect() {
            return if_safeinspect;
        }

        public void setIf_safeinspect(String if_safeinspect) {
            this.if_safeinspect = if_safeinspect;
        }

        public String getInitial_inspect_mark() {
            return initial_inspect_mark;
        }

        public void setInitial_inspect_mark(String initial_inspect_mark) {
            this.initial_inspect_mark = initial_inspect_mark;
        }

        public String getInitial_inspect_no() {
            return initial_inspect_no;
        }

        public void setInitial_inspect_no(String initial_inspect_no) {
            this.initial_inspect_no = initial_inspect_no;
        }

        public String getInspect_date() {
            return inspect_date;
        }

        public void setInspect_date(String inspect_date) {
            this.inspect_date = inspect_date;
        }

        public String getInspect_no() {
            return inspect_no;
        }

        public void setInspect_no(String inspect_no) {
            this.inspect_no = inspect_no;
        }

        public String getInspect_org() {
            return inspect_org;
        }

        public void setInspect_org(String inspect_org) {
            this.inspect_org = inspect_org;
        }

        public String getInspect_time_limit() {
            return inspect_time_limit;
        }

        public void setInspect_time_limit(String inspect_time_limit) {
            this.inspect_time_limit = inspect_time_limit;
        }

        public String getInspect_type() {
            return inspect_type;
        }

        public void setInspect_type(String inspect_type) {
            this.inspect_type = inspect_type;
        }

        public String getInspector_code() {
            return inspector_code;
        }

        public void setInspector_code(String inspector_code) {
            this.inspector_code = inspector_code;
        }

        public String getInspector_name() {
            return inspector_name;
        }

        public void setInspector_name(String inspector_name) {
            this.inspector_name = inspector_name;
        }

        public String getIs_special_inspect() {
            return is_special_inspect;
        }

        public void setIs_special_inspect(String is_special_inspect) {
            this.is_special_inspect = is_special_inspect;
        }

        public String getOrg_code() {
            return org_code;
        }

        public void setOrg_code(String org_code) {
            this.org_code = org_code;
        }

        public String getPratice_inspector_code() {
            return pratice_inspector_code;
        }

        public void setPratice_inspector_code(String pratice_inspector_code) {
            this.pratice_inspector_code = pratice_inspector_code;
        }

        public String getPratice_inspector_name() {
            return pratice_inspector_name;
        }

        public void setPratice_inspector_name(String pratice_inspector_name) {
            this.pratice_inspector_name = pratice_inspector_name;
        }

        public String getPriority_order() {
            return priority_order;
        }

        public void setPriority_order(String priority_order) {
            this.priority_order = priority_order;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getRisk_attribute() {
            return risk_attribute;
        }

        public void setRisk_attribute(String risk_attribute) {
            this.risk_attribute = risk_attribute;
        }

        public String getShip_id() {
            return ship_id;
        }

        public void setShip_id(String ship_id) {
            this.ship_id = ship_id;
        }

        public String getShip_manager() {
            return ship_manager;
        }

        public void setShip_manager(String ship_manager) {
            this.ship_manager = ship_manager;
        }

        public String getShip_manager_addr() {
            return ship_manager_addr;
        }

        public void setShip_manager_addr(String ship_manager_addr) {
            this.ship_manager_addr = ship_manager_addr;
        }

        public String getShip_manager_fax() {
            return ship_manager_fax;
        }

        public void setShip_manager_fax(String ship_manager_fax) {
            this.ship_manager_fax = ship_manager_fax;
        }

        public String getShip_name_cn() {
            return ship_name_cn;
        }

        public void setShip_name_cn(String ship_name_cn) {
            this.ship_name_cn = ship_name_cn;
        }

        public String getShip_no() {
            return ship_no;
        }

        public void setShip_no(String ship_no) {
            this.ship_no = ship_no;
        }

        public String getShip_operator() {
            return ship_operator;
        }

        public void setShip_operator(String ship_operator) {
            this.ship_operator = ship_operator;
        }

        public String getShip_operator_addr() {
            return ship_operator_addr;
        }

        public void setShip_operator_addr(String ship_operator_addr) {
            this.ship_operator_addr = ship_operator_addr;
        }

        public String getShip_operator_fax() {
            return ship_operator_fax;
        }

        public void setShip_operator_fax(String ship_operator_fax) {
            this.ship_operator_fax = ship_operator_fax;
        }

        public String getSpot_busi_no() {
            return spot_busi_no;
        }

        public void setSpot_busi_no(String spot_busi_no) {
            this.spot_busi_no = spot_busi_no;
        }

        public String getTask_id() {
            return task_id;
        }

        public void setTask_id(String task_id) {
            this.task_id = task_id;
        }

        public int getUnclosed_num() {
            return unclosed_num;
        }

        public void setUnclosed_num(int unclosed_num) {
            this.unclosed_num = unclosed_num;
        }

        public String getVoyage_id() {
            return voyage_id;
        }

        public void setVoyage_id(String voyage_id) {
            this.voyage_id = voyage_id;
        }
    }
}
