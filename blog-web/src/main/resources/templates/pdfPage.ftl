<!DOCTYPE html>
<html>
<head>
    <title>Spring Boot Demo - PDF</title>
    <link href="http://localhost:9091/css/pdf.css" rel="stylesheet" type="text/css"/>
    <#--    <link href="http://localhost:9091/css/bootstrap-clearmin.min.css"/>-->
    <script src="http://localhost:9091/js/jquery.min.js" type="javascript"></script>
    <script src="http://localhost:9091/js/bootstrap.min.js" type="javascript"></script>
    <script src=""></script>
    <style>
        @page {
            size: A4;/*设置纸张大小:A4(210mm 297mm)、A3(297mm 420mm) 横向则反过来*/
            margin: 0.25in;
            padding: 1em;
            @bottom-center {
                content: "Watsons © 版权所有";
                font-family: SimSun;
                font-size: 12px;
                color: red;
            };
            @top-center {
                content: element(header)
            };
            @bottom-right {
                content: "第" counter(page) "页  共 " counter(pages) "页";
                font-family: SimSun;
                font-size: 12px;
                color: #000;
            };
        }
    </style>
</head>
<body style="font-family: SimSun">
<#--<div id="signImg"></div>-->

<#--<div>5.普通table表格</div>-->
<div class="out_borad">
    <table>
        <tr>
            <td><p>Employee Code</p>
                <p>工号</p>
            </td>
            <td>
                ${currentUser.employeeNo}
            </td>
            <td>Chinese Name <p>中文名</p>
            </td>
            <td>
                ${currentUser.chineseName}
            </td>
            <td>English Name <p>英文名</p>
            </td>
            <td>
                ${currentUser.englishName}
            </td>
        </tr>
        <tr>
            <td>Department/Store <p>部门/店铺</p></td>
            <td>${currentUser.deptNo}</td>
            <td>Join-in Date<p>入职日期(年月日)</p></td>
            <td>${currentUser.dateJoin}</td>
            <td>Grade <p>级别</p></td>
            <td>${currentUser.grade}</td>
        </tr>
        <tr>
            <td rowspan="2">Resignation Reason <p>辞职原因</p></td>
            <td colspan="5" style="border: 0;text-align: left">
                Owing to (reason)<span class="user_fill-in">   不知道什么原因   </span>
                , I (name) submitted resignation notice to my line manager on (date)
                <span class="user_fill-in">   ${nowDate?string("yyyy-MM-dd")}   </span>.
                <p style="margin-top: 10px"></p>
                本人 (中文名）<span class="user_fill-in">   ${currentUser.chineseName}   </span>于<span
                        class="user_fill-in">   ${nowDate?string("yyyy年MM月dd日")}   </span>
                因 <span class="user_fill-in">   不知道什么原因   </span>(原因）向部门经理提出辞职。
            </td>
        </tr>
        <tr>
            <td colspan="3" style="border: 0"></td>
            <td style="background-color: #ffc5b9;border-right: 0;text-align: left">HR Notes: <p style="font-size: 10px">
                    (for Voluntary only)</p>
            </td>
            <td style="background-color: #ffc5b9;border-left: 0;"></td>
        </tr>
        <tr>
            <td rowspan="2">Declaration <p>声明</p></td>
            <td colspan="5" style="text-align: left;border-bottom: 0">According to the Watsons Employee Handbook that I
                signed off on the report duty date, I
                understand that I have the obligation to comply with the Watsons Confidentiality Policy while I am
                employed and after I leave the company. I do not disclose to any third party any information relating to
                business correspondences and documents, financial information, operation and trade information, and
                client and customer information as well.
                <p></p>
                根据本人在入职时签收的《屈臣氏员工手册》，本人在离职后将继续遵守保密义务，不会泄露公司
                的任何机密及书信、账户、交易内容等信息，也不会泄露本人在工作期间获取的客户的任何信息资料。
            </td>
        </tr>
        <tr>
            <td style="border: 0"></td>
            <td style="border: 0"> Signature<p>员工签名:</p></td>
            <td style="border: 0"></td>
            <td style="border: 0"> Date <p>日期:</p></td>
            <td style="border: 0"></td>
        </tr>

        <#--第五行 start-->
        <tr>
            <td rowspan="5">Department Comments <p>部门意见</p></td>
            <td colspan="5" style="text-align: left">The Resignation Notice of the above employee was received. Having
                discussed with the employee, I hereby endorse his/her notice. His/her last working day will be
                <span class="user_fill-in"></span>(YY/MM/DD).
                <p style="margin-top: 10px"></p>
                已收到该员工的辞职通知。经双方协商一致，确认该员工的最后工作日为<span class="user_fill-in"></span>年 月 日。
            </td>
        </tr>
        <tr>
            <td style="border: 0">Line Manager Signature <p>直属主管签名</p></td>
            <td style="border: 0;padding-top: 30px"><span class="user_fill-in">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;</span>
            </td>
            <td style="border: 0"></td>
            <td style="border: 0">Next Level Manager Signature (if applicable) <p>上级主管签名</p>
                <p>（如适用）</p></td>
            <td style="border: 0;padding-top: 30px"><span class="user_fill-in">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;</span>
            </td>
        </tr>
        <tr>
            <td style="border: 0">Date 日期</td>
            <td style="border: 0;padding-top: 30px;"><span class="user_fill-in">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;</span>
            </td>
            <td style="border: 0"></td>
            <td style="border: 0">Date 日期</td>
            <td style="border: 0;padding-top: 30px;border-right: 1px solid"><span class="user_fill-in">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;</span>
            </td>
        </tr>
        <tr>
            <td style="border: 0;font-size: 10px" colspan="2">(REMINDER: 区域经理/店铺经理在批复店铺员工 的离职通知后，须同时将此通知交给区域/市场人
                力资源部薪酬福利组。）
            </td>
            <td style="border: 0"></td>
            <td style="border: 0">Staff Signature <p>员工确认</p></td>
            <td style="border: 0;padding-top: 30px;border-right: 1px solid"><span class="user_fill-in">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;</span>
            </td>
        </tr>
        <tr>
            <td style="border: 0" colspan="3"></td>
            <td style="border: 0">Date <p>日期</p></td>
            <td style="border: 0;padding-top: 30px"><span class="user_fill-in">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;</span>
            </td>
        </tr>
        <#--第五行 end-->
        <tr>
            <td>HR Remark<p>HR备注</p></td>
            <td colspan="3" style="text-align: left;background-color: #ffc5b9">Leaving Reason Code<p>离职原因代码</p></td>
            <td colspan="2" style="text-align: left;background-color: #ffc5b9">Notice Received Date: <p>HR于（日期）<span
                            class="user_fill-in">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;</span>
                    收到此辞职通知</p></td>
        </tr>
    </table>
    <p>NOTES:</p>
    <p> 1) This form applies to all regular staff inclusive of DC.此辞职通知适用于屈臣氏中国区所有员工，第三方员工除外。</p>

    <p> 2) The resigned staff office only) is requested to submit this notice to local HR-C&#38;B Team in the 1st place
        once
        completed the sign-offprocess.</p>

    <p> 3) For Offices/DC, this resignation notice shall be endorsed by Line Manager and next level Manager (if needed)
        as means of acknowledgment.The last working date shall also be confirmed by both parties with staff signature.
        This form and Exit Checklist are administrated by Local HR-C&#38;B.</p>

    <p><span class="user_fill-in">办公室及仓库员工</span>，辞职通知由直线主管及再上一-级主管(如适用)签名，双方确定最后工作日且员工签名确认。此辞职通知与离职结算清单由所属区域/市場的人力资源部薪酬福利组存档。
    </p>

    <p>4) For Store general staff, the resignation notice shall be signed by Store Manager. For Key Holders and above,
        the resignation notice shall besigned by Line manager and next level Manager.</p>

    <p><span class="user_fill-in">店铺工作的普通级别员工</span>，辞职通知须由店铺经理签名:<span class="user_fill-in">当班及以上级别的员工</span>，店铺经理签名后须由再上一级主管签名
        。</p>
</div>
</body>
</html>