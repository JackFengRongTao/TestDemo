package com.fengtest;

public class Testjava {
    public static void main(String[] args) {

        String str1 = "insert into lc_GDSY_YDGZPJ_zxxx\n" +
                "  (id,\n" +
                "   xmlx,\n" +
                "   zxqc,\n" +
                "   zxjc,\n" +
                "   zxdm,\n" +
                "   fxqc,\n" +
                "   ssrq,\n" +
                "   zqqxr,\n" +
                "   zxrq,\n" +
                "   zqdqr,\n" +
                "   zqdfr,\n" +
                "   xm,\n" +
                "   zxxx,\n" +
                "   lc_gdsy_ydgzpj_id,\n" +
                "   fxqx,\n" +
                "   dqye,\n" +
                "   yjfxr\n" +
                "   )\n" +
                "  select func_nextid('lc_GDSY_YDGZPJ_zxxx'),    \n" +
                "         {2},\n" +
                "         xx.w_zqmc,\n" +
                "         xx.w_zqjc,\n" +
                "         xx.w_zqdm,\n" +
                "         xx.w_fxqc,\n" +
                "         xx.w_ssrq,\n" +
                "         xx.w_jxqsr,\n" +
                "         xx.w_jxjzr,\n" +
                "         xx.w_dqr,\n" +
                "         xx.w_dfr,\n" +
                "         xx.xm,\n" +
                "         xx.id,\n" +
                "         {0},\n" +
                "         xx.w_fxqx,\n" +
                "         xx.w_dqye,\n" +
                "         xx.yj_fxr\n" +
                "    from (select  distinct a.w_zqdm      from t_gdsy_zxxx a where a.xm ={1} ) b, t_gdsy_zxxx xx\n" +
                "   where b.w_zqdm = xx.w_zqdm and xx.xm = {1}\n" +
                "   and to_date(xx.w_jxqsr,'yyyymmdd') < sysdate and to_date(xx.w_dfr,'yyyymmdd')>sysdate";

        String str2 = " insert into lc_GDSY_YDGZPJ_zxxx\n" +
                "  (id,\n" +
                "   xmlx,\n" +
                "   zxqc,\n" +
                "   zxjc,\n" +
                "   zxdm,\n" +
                "   fxqc,\n" +
                "   ssrq,\n" +
                "   zqqxr,\n" +
                "   zxrq,\n" +
                "   zqdqr,\n" +
                "   zqdfr,\n" +
                "   xm,\n" +
                "   zxxx,\n" +
                "   lc_gdsy_ydgzpj_id,\n" +
                "   fxqx,\n" +
                "   dqye,\n" +
                "   yjfxr\n" +
                "   )\n" +
                "  select func_nextid('lc_GDSY_YDGZPJ_zxxx'),    \n" +
                "         {2},\n" +
                "         xx.w_zqmc,\n" +
                "         xx.w_zqjc,\n" +
                "         xx.w_zqdm,\n" +
                "         xx.w_fxqc,\n" +
                "         xx.w_ssrq,\n" +
                "         xx.w_jxqsr,\n" +
                "         xx.w_jxjzr,\n" +
                "         xx.w_dqr,\n" +
                "         xx.w_dfr,\n" +
                "         xx.xm,\n" +
                "         xx.id,\n" +
                "         {0},\n" +
                "         xx.w_fxqx,\n" +
                "         xx.w_dqye,\n" +
                "         xx.yj_fxr\n" +
                "    from (select  distinct a.w_zqdm      from t_gdsy_zxxx a where a.xm ={1} ) b, t_gdsy_zxxx xx\n" +
                "   where b.w_zqdm = xx.w_zqdm and xx.xm = {1}\n" +
                "   and to_date(xx.w_jxqsr,'yyyymmdd') < sysdate and to_date(xx.w_dfr,'yyyymmdd')>sysdate";

        String str3 = " insert into lc_GDSY_YDGZPJ_zxxx\n" +
                "  (id,\n" +
                "   xmlx,\n" +
                "   zxqc,\n" +
                "   zxjc,\n" +
                "   zxdm,\n" +
                "   fxqc,\n" +
                "   ssrq,\n" +
                "   zqqxr,\n" +
                "   zxrq,\n" +
                "   zqdqr,\n" +
                "   zqdfr,\n" +
                "   xm,\n" +
                "   zxxx,\n" +
                "   lc_gdsy_ydgzpj_id,\n" +
                "   fxqx,\n" +
                "   dqye,\n" +
                "   yjfxr   \n" +
                "   )\n" +
                "  select func_nextid('lc_GDSY_YDGZPJ_zxxx'),    \n" +
                "         {2},\n" +
                "         xx.w_zqmc,\n" +
                "         xx.w_zqjc,\n" +
                "         xx.w_zqdm,\n" +
                "         xx.w_fxqc,\n" +
                "         xx.w_ssrq,\n" +
                "         xx.w_jxqsr,\n" +
                "         xx.w_jxjzr,\n" +
                "         xx.w_dqr,\n" +
                "         xx.w_dfr,\n" +
                "         xx.xm,\n" +
                "         xx.id,\n" +
                "         {0},\n" +
                "         xx.w_fxqx,\n" +
                "         xx.w_dqye,\n" +
                "         xx.yj_fxr\n" +
                "    from (select  distinct a.w_zqdm   from t_gdsy_zxxx a where a.xm ={1} ) b, t_gdsy_zxxx xx\n" +
                "   where b.w_zqdm = xx.w_zqdm and xx.xm = {1}\n" +
                "   and to_date(xx.w_jxqsr,'yyyymmdd') < sysdate and to_date(xx.w_dfr,'yyyymmdd')>sysdate";


        System.out.println(str1.replace(" ",""));
        System.out.println(str2.replace(" ",""));
        System.out.println(str3.replace(" ",""));
        System.out.println(str1.replace(" ","").equals(str2.replace(" ","")));
        System.out.println(str2.replace(" ","").equals(str3.replace(" ","")));

    }
}
