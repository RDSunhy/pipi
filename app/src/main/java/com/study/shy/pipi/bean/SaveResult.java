package com.study.shy.pipi.bean;

public class SaveResult {

    /**
     * retCode : 200
     * msg : success
     * result : {"k":"mobile","v":"{mobile1:'14782867238'}"}
     */

    private String retCode;
    private String msg;
    private ResultBean result;

    @Override
    public String toString() {
        return "SaveResult{" +
                "retCode='" + retCode + '\'' +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * k : mobile
         * v : {mobile1:'14782867238'}
         */

        private String k;
        private String v;

        @Override
        public String toString() {
            return "ResultBean{" +
                    "k='" + k + '\'' +
                    ", v='" + v + '\'' +
                    '}';
        }

        public String getK() {
            return k;
        }

        public void setK(String k) {
            this.k = k;
        }

        public String getV() {
            return v;
        }

        public void setV(String v) {
            this.v = v;
        }
    }
}
