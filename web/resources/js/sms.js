/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function ano()
{
    var combo = document.getElementById('smstoclients:ano');
    console.log("hello this clicked");
    if (combo.checked === true)
    {
        var sms = document.getElementById('smstoclients:sms').value;
        sms = sms + '{AgentNo}';
        document.getElementById('smstoclients:sms').value = sms;
    } else
    {
        var sms = document.getElementById('smstoclients:sms').value;
        sms = sms.replace(/{AgentNo}/g, "");
        document.getElementById('smstoclients:sms').value = sms;
    }
}
function an()
{
    var combo = document.getElementById('smstoclients:an');
    if (combo.checked === true)
    {
        var sms = document.getElementById('smstoclients:sms').value;
        sms = sms + '{AgentName}';
        document.getElementById('smstoclients:sms').value = sms;
    } else
    {
        var sms = document.getElementById('smstoclients:sms').value;
        sms = sms.replace(/{AgentName}/g, "");
        document.getElementById('smstoclients:sms').value = sms;
    }
}
function em()
{
    var combo = document.getElementById('smstoclients:em');
    if (combo.checked === true)
    {
        var sms = document.getElementById('smstoclients:sms').value;
        sms = sms + '{Email}';
        document.getElementById('smstoclients:sms').value = sms;
    } else
    {
        var sms = document.getElementById('smstoclients:sms').value;
        sms = sms.replace(/{Email}/g, "");
        document.getElementById('smstoclients:sms').value = sms;
    }
}
function cdt()
{
    var combo = document.getElementById('smstoclients:cdt');
    if (combo.checked === true)
    {
        var sms = document.getElementById('smstoclients:sms').value;
        sms = sms + '{CommenceDt}';
        document.getElementById('smstoclients:sms').value = sms;
    } else
    {
        var sms = document.getElementById('smstoclients:sms').value;
        sms = sms.replace(/{CommenceDt}/g, "");
        document.getElementById('smstoclients:sms').value = sms;
    }
}

function bc()
{
    var combo = document.getElementById('smstoclients:bc');
    if (combo.checked === true)
    {
        var sms = document.getElementById('smstoclients:sms').value;
        sms = sms + '{BranchCd}';
        document.getElementById('smstoclients:sms').value = sms;
    } else
    {
        var sms = document.getElementById('smstoclients:sms').value;
        sms = sms.replace(/{BranchCd}/g, "");
        document.getElementById('smstoclients:sms').value = sms;
    }
}
function bn()
{
    var combo = document.getElementById('smstoclients:bn');
    if (combo.checked === true)
    {
        var sms = document.getElementById('smstoclients:sms').value;
        sms = sms + '{BranchNm}';
        document.getElementById('smstoclients:sms').value = sms;
    } else
    {
        var sms = document.getElementById('smstoclients:sms').value;
        sms = sms.replace(/{BranchNm}/g, "");
        document.getElementById('smstoclients:sms').value = sms;
    }
}
function uc()
{
    var combo = document.getElementById('smstoclients:uc');
    if (combo.checked === true)
    {
        var sms = document.getElementById('smstoclients:sms').value;
        sms = sms + '{UnitCd}';
        document.getElementById('smstoclients:sms').value = sms;
    } else
    {
        var sms = document.getElementById('smstoclients:sms').value;
        sms = sms.replace(/{UnitCd}/g, "");
        document.getElementById('smstoclients:sms').value = sms;
    }
}
function un()
{
    var combo = document.getElementById('smstoclients:un');
    if (combo.checked === true)
    {
        var sms = document.getElementById('smstoclients:sms').value;
        sms = sms + '{UnitNm}';
        document.getElementById('smstoclients:sms').value = sms;
    } else
    {
        var sms = document.getElementById('smstoclients:sms').value;
        sms = sms.replace(/{UnitNm}/g, "");
        document.getElementById('smstoclients:sms').value = sms;
    }
}
function alertTest()
{
    alert("Danger! Danger!");
}

//ENd of Agent SMS
//Begiing of cliet sms
function cn()
{
    var combo = document.getElementById('smstoclients:cn');
    if (combo.checked === true)
    {
        var sms = document.getElementById('smstoclients:sms').value;
        sms = sms + '{ClientName}';
        document.getElementById('smstoclients:sms').value = sms;
    } else
    {
        var sms = document.getElementById('smstoclients:sms').value;
        sms = sms.replace(/{ClientName}/g, "");
        document.getElementById('smstoclients:sms').value = sms;
    }
}

function pn()
{
    var combo = document.getElementById('smstoclients:pn');
    if (combo.checked === true)
    {
        var sms = document.getElementById('smstoclients:sms').value;
        sms = sms + '{PolicyNo}';
        document.getElementById('smstoclients:sms').value = sms;
    } else
    {
        var sms = document.getElementById('smstoclients:sms').value;
        sms = sms.replace(/{PolicyNo}/g, "");
        document.getElementById('smstoclients:sms').value = sms;
    }
}

function md()
{
    var combo = document.getElementById('smstoclients:md');
    if (combo.checked === true)
    {
        var sms = document.getElementById('smstoclients:sms').value;
        sms = sms + '{MaturityDt}';
        document.getElementById('smstoclients:sms').value = sms;
    } else
    {
        var sms = document.getElementById('smstoclients:sms').value;
        sms = sms.replace(/{MaturityDt}/g, "");
        document.getElementById('smstoclients:sms').value = sms;
    }
}

function lpd()
{
    var combo = document.getElementById('smstoclients:lpd');
    if (combo.checked === true)
    {
        var sms = document.getElementById('smstoclients:sms').value;
        sms = sms + '{LastPremDt}';
        document.getElementById('smstoclients:sms').value = sms;
    } else
    {
        var sms = document.getElementById('smstoclients:sms').value;
        sms = sms.replace(/{LastPremDt}/g, "");
        document.getElementById('smstoclients:sms').value = sms;
    }
}
function pdd()
{
    var combo = document.getElementById('smstoclients:pdd');
    if (combo.checked === true)
    {
        var sms = document.getElementById('smstoclients:sms').value;
        sms = sms + '{PremDueDt}';
        document.getElementById('smstoclients:sms').value = sms;
    } else
    {
        var sms = document.getElementById('smstoclients:sms').value;
        sms = sms.replace(/{PremDueDt}/g, "");
        document.getElementById('smstoclients:sms').value = sms;
    }
}

function mp()
{
    var combo = document.getElementById('smstoclients:mp');
    if (combo.checked === true)
    {
        var sms = document.getElementById('smstoclients:sms').value;
        sms = sms + '{ModalPrem}';
        document.getElementById('smstoclients:sms').value = sms;
    } else
    {
        var sms = document.getElementById('smstoclients:sms').value;
        sms = sms.replace(/{ModalPrem}/g, "");
        document.getElementById('smstoclients:sms').value = sms;
    }
}

function lb()
{
    var combo = document.getElementById('smstoclients:lb');
    if (combo.checked === true)
    {
        var sms = document.getElementById('smstoclients:sms').value;
        sms = sms + '{LoanBal}';
        document.getElementById('smstoclients:sms').value = sms;
    } else
    {
        var sms = document.getElementById('smstoclients:sms').value;
        sms = sms.replace(/{LoanBal}/g, "");
        document.getElementById('smstoclients:sms').value = sms;
    }
}


//End of client sms......??????????????????????

// <<<<<<<<<<<<<<<<<<<<<<<<<<<<SMS to allocte
//var array = new Array();
//var i = 0;
//function getTicket(ticket, check) {
//
//    var a = array.indexOf(ticket);
//    if (a != -1) {
//        check = true;
//
//    }
//    if (check == false) {
//        //   alert(check);
//        array.push(ticket);
//    } else if (check == true) {
//        var index = array.indexOf(ticket);
//        // alert(index);
//        array.splice(index, 1);
//    }
//}
//// alert(ticket+" "+check+" ");
//
//function deleteRule()
//{
//    /// alert(array.toString());
//    if (array.length > 0) {
//        // alert(array.toString());
//        if (confirm('Are you sure You want to Delete these Messages?')) {
//            // do things if OK
//            document.location.href = 'delete.jsf?deletetick=' + array + '';
//        }
//
//    } else {
//
//
//        alert("No Message selected!!");
//    }
//
//}
//function allocateRule()
//{
//
//    if (array.length > 0) {
//        // alert(array.toString());
//        if (confirm('Are you sure You want to Allocate these Messages?')) {
//            // do things if OK
//            document.location.href = 'allocateMany.jsf?allocatetick=' + array + '';
//        }
//
//    } else {
//
//
//        alert("No Message selected!!");
//    }
//
//}
//function getId() {
//    alert("runnning");
//    var cid = 'pendingTasks:table:' + 390 + ':check';
//    document.getElementById(cid).checked = true;
//
//    alert(document.getElementById(cid).checked);
//}
//
//function setChecked() {
//
//
//    var trows = document.getElementById("nrows").value;
//    var pages = document.getElementById("pages1").value;
//    alert(pages);
//    alert("trows is a number " + (trows - 1));
//    if (array.length === 0) {
//
//        alert("here");
//    } else {
//
//        for (var i = 21; i < trows - 1; i++) {
//
//            var cid = 'pendingTasks:table:' + i + ':check';
//
//            var hidd = 'pendingTasks:table:' + i + ':hid';
//            var cticket = document.getElementById(hidd).value;
//            alert(document.getElementById(hidd).value + "  " + array.toString());
//            //cticket="135772";
//
//            //  alert(array.indexOf(cticket));
//            if (array.toString().contains(cticket)) {
//                // alert("result found");
//                // alert( document.getElementById(hidd).value);
//                document.getElementById(cid).checked = true;
//
//            } else {
//
//                //  alert("sth diff");
//            }
//
//        }
//
//    }
//
//}
//
//function showSelected() {
//    if (array.length > 0) {
//        // alert(array.toString());
//        // if (confirm('Are you sure You want to Allocate these Messages?')) { 
//        // do things if OK
//        window.open('viewSelected.jsf?allocatetick=' + array + '');
//
//        //}  
//
//    } else {
//
//
//        alert("No Message selected!!");
//    }
//
//}

//-----------------------------------------------all public messages-------------------------------
//function changeVal(curValue) {
//    console.log("Setting Value: "+curValue);
//    document.getElementById('myvalueChage').innerHTML=curValue;
//   // document.location.href = "changeMsgValue.jsf?checkval=" + curValue + "&allocuser=" + userId + "&id=" + id + "";
//}

//------------------------------------------------------Dashboard--------------------------------------------
function ext1()
{
    // Set bar width
    this.cfg.seriesDefaults.rendererOptions
            = {
                barWidth: 100

            };
}
function chartExtender1()
{

    this.cfg.legend
            = {
                fontSize: '14px'
                        //  rendererOptions :
            };
}
function chartExtender()
{
    //bold gridlines
    this.cfg.grid = {
        gridLineColor: '#303030',
        gridLIneWidth: 20.0
    };
}
var html = document.getElementById('htmlCont');
//  var imageElement = html.getElementsByTagNameNS("image");

function removeImage(imageElement) {
    imageElement;
}

///>>>>>>>>>>>>>>>>>>>>>BILING>>>>>>>>>>>>>>>>>>
$(document).ready(function ()
{
    $(".monthPicker").datepicker({
        dateFormat: 'MM yy',
        changeMonth: true,
        changeYear: true,
        showButtonPanel: true,

        onClose: function (dateText, inst) {
            var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
            var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
            var date = $(this).val($.datepicker.formatDate('MM yy', new Date(year, month, 1)));
            document.getElementById('monthval').value = month;
            document.getElementById('yearval').value = year;

        }
    });

    $(".monthPicker").focus(function () {
        $(".ui-datepicker-calendar").hide();
        $("#ui-datepicker-div").position({
            my: "center top",
            at: "center bottom",
            of: $(this)
        });
    });
});

function start() {
    PF('statusDialog').show();
}

function stop() {
    PF('statusDialog').hide();
}
