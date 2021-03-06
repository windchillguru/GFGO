<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.ptc.com/windchill/taglib/fmt" prefix="fmt"%>
<%@ taglib prefix="jca" uri="http://www.ptc.com/windchill/taglib/components"%>
<%@ taglib uri="http://www.ptc.com/windchill/taglib/changeWizards" prefix="cwiz"%>
<%@ page import="wt.fc.Persistable"%>
<%@ page import="wt.change2.ChangeOrder2" %>
<%@ taglib uri="http://www.ptc.com/windchill/taglib/components" prefix="jca"%>
<%@ taglib uri="http://www.ptc.com/windchill/taglib/mvc" prefix="mvc"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib prefix="wctags" tagdir="/WEB-INF/tags" %>
<%@ include file="/netmarkets/jsp/util/begin.jspf"%>
<%@page import="wt.httpgw.URLFactory"%>

<jsp:include page="${mvc:getComponentURL('wt.change2.ChangeActivity2.changeTask.affectedItemsTable')}" flush="true"/>

<jsp:include page="${mvc:getComponentURL('ext.appo.ecn.mvc.builder.AffectedEndItemsTableBuilder')}" flush="true"/>

<jsp:include page="${mvc:getComponentURL('ext.appo.ecn.mvc.builder.ChangeTaskTableBuilder')}" flush="true" />

<input type="hidden" name="datasArray" id="datasArray"></input>

<input type="hidden" name="changeTaskArray" id="changeTaskArray"></input>

<input type="hidden" name="affectedProductID" id="affectedProductID"></input>

<script type="text/javascript">
	var table_id = "ext.appo.ecn.mvc.builder.ChangeTaskTableBuilder";
	// 移除事务性任务
	function deleteChangeTask(){
		var selectRows = PTC.jca.table.Utils.getTableSelectedRowsById(table_id, false, false);
		if(selectRows.length == 0){
			alert("至少选择一项进行删除!") ;
			return ;
		}
		// 存储表单数据
		var datasArray = eval("("+document.getElementById("datasArray").value+")");
        var table = PTC.jca.table.Utils.getTable(table_id);
        var rowDatas = PTC.jca.table.Utils.getRowData(table);
        var rowCount = rowDatas.length;

        var oidArray = new Array();
        for(var i=0; i<selectRows.length; i++){
            var row = selectRows[i];
            var rowValue = getOidFromRowValue(row.value);
			delete datasArray[rowValue]; 
            oidArray.push(rowValue);
        }
        PTC.jca.table.Utils.removeRows(table, oidArray);
		
		document.getElementById("datasArray").value = JSON.stringify(datasArray) ;
    }
	
	// 添加数据至事务性任务
	function addChangeTask(){
		// 获取表单中所有存储的数据
		var allDatas = {} ;
		var grid = window.PTC.jca.table.Utils.getTable(table_id);
		var store = grid.getStore();
		store.each(function(record){
			var json = {};
			if(record.data.changeTheme){
				json['changeTheme'] = record.data.changeTheme;
			}
			if(record.data.changeDescribe){
				json['changeDescribe'] = record.data.changeDescribe;
			}
			if(record.data.responsible){
				json['responsible'] = record.data.responsible;
			}
			if(record.data.changeActivity2){
				json['changeActivity2'] = record.data.changeActivity2;
			}
			allDatas[record.data.oid] = json ;
		});
		// 执行创建操作
		createEditChangeTask('', JSON.stringify(allDatas)) ;
	}
	
	// 编辑事务性任务
	function editChangeTask(){
		// 选择的对象
		var selectRows = PTC.jca.table.Utils.getTableSelectedRowsById(table_id, false, false);
		if(selectRows.length != 1){
			alert("必须选择一项或只能选择一项进行编辑!") ;
			return ;
		}
		// 获取表单中所有存储的数据
		var allDatas = {} ;
		var grid = window.PTC.jca.table.Utils.getTable(table_id);
		var store = grid.getStore();
		store.each(function(record){
			var json = {};
			if(record.data.changeTheme){
				json['changeTheme'] = record.data.changeTheme;
			}
			if(record.data.changeDescribe){
				json['changeDescribe'] = record.data.changeDescribe;
			}
			if(record.data.responsible){
				json['responsible'] = record.data.responsible;
			}
			if(record.data.changeActivity2){
				json['changeActivity2'] = record.data.changeActivity2;
			}
			allDatas[record.data.oid] = json ;
		});
		// 选择的对象ID
		var seleteOID = getOidFromRowValue(selectRows[0].value);
		// 执行编辑动作
		createEditChangeTask(seleteOID, JSON.stringify(allDatas)) ;
	}
	
	// 查询用户
	function searchUser(obj){
		var userName = obj.value ;
		if(userName.length == 0){
			alert('责任人不能为空!') ;
			return ;
		}
		var params = "userName=" + userName;
		var url = "netmarkets/jsp/ext/appo/changeNotice/searchUserPicker.jsp";
		var json = eval("("+ajaxRequest(url, params)+")");
		//错误信息
		var errorInfo = json['message'];
        if(errorInfo.length != 0){
			obj.value = "" ;
        	alert(errorInfo);
        	return;
        }
		obj.value = json['resultDatas'] ;
		
		saveChangeTaskArray() ;
	}
	
	// 创建及编辑事务性任务
	function createEditChangeTask(seleteOID, allDatas){
  	    var href = document.location.href;
        var wcurl = href.substring(0,href.indexOf(document.location.pathname))+"/Windchill/";  
  	    window.open(wcurl + 'netmarkets/jsp/ext/appo/changeNotice/operationTransactionalChangeActivity2.jsp?allDatas='+allDatas + "&seleteOID=" + seleteOID,
  	    '_blank','toolbar=no,menubar=no,location=no,status=no,resizable=no,height=400,width=700,left=600,top=300');
    }
	
	// 重新加载 ChangeTaskTableBuilder
	function reloadTable(param){
		document.getElementById("datasArray").value = param ;
		PTC.jca.table.Utils.reload(table_id,{changeTaskBeanID:param},true);
	}
	
	// 添加数据至 AffectedEndItemsTableBuilder 中
	function addAffectedEndItems(){
		var href = document.location.href;
        var wcurl = href.substring(0,href.indexOf(document.location.pathname))+"/Windchill/";  
  	    window.open(wcurl + 'netmarkets/jsp/ext/appo/changeNotice/searchProductPicker.jsp',
  	    '_blank','toolbar=no,menubar=no,location=no,status=no,resizable=no,height=400,width=700,left=600,top=300');
	}
	
	// 移除  AffectedEndItemsTableBuilder 中数据
	function deleteAffectedEndItems(){
		var selectRows = PTC.jca.table.Utils.getTableSelectedRowsById('ext.appo.ecn.mvc.builder.AffectedEndItemsTableBuilder', false, false);
		if(selectRows.length == 0){
			alert("至少选择一项进行删除!") ;
			return ;
		}	
		var table = PTC.jca.table.Utils.getTable('ext.appo.ecn.mvc.builder.AffectedEndItemsTableBuilder');
        var rowDatas = PTC.jca.table.Utils.getRowData(table);
        var rowCount = rowDatas.length;
		// 记录AffectedEndItemsTableBuilder中所有数据
		var affectedProductID = eval("("+document.getElementById("affectedProductID").value+")");
        var oidArray = new Array();
        for(var i=0; i<selectRows.length; i++){
            var row = selectRows[i];
            var rowValue = getOidFromRowValue(row.value);
			if(affectedProductID.indexOf(rowValue) != -1){
				affectedProductID.splice(affectedProductID.indexOf(rowValue), 1) ;
			}
            oidArray.push(rowValue);
        }
        PTC.jca.table.Utils.removeRows(table, oidArray);
		document.getElementById("affectedProductID").value = JSON.stringify(affectedProductID) ;
	}
	
	// 收集受影响对象表中选中数据的上传产品对象
	function collectUpperProduct(){
		var selectRows = PTC.jca.table.Utils.getTableSelectedRowsById('changeTask_affectedItems_table', false, false);
		if(selectRows.length == 0){
			alert("至少选择一条数据进行收集操作!") ;
			return ;
		}
		var oidArray = new Array();
        for(var i = 0; i < selectRows.length ; i++){
            oidArray.push(getOidFromRowValue(selectRows[i].value));
        }
		if(oidArray.length > 0){
			// 检查选择产品是否符合要求
			var params = "itemOid=" + JSON.stringify(oidArray) + "&method=collectAffectedEndItems";
			var url = "netmarkets/jsp/ext/appo/changeNotice/affectedEndItems.jsp";
			var json = eval("("+ajaxRequest(url, params)+")");
			// 数据反填
			reloadAffectedEndItemsTable(json['resultDatas']) ;
		}
	}
	
	// 重新加载 AffectedEndItemsTableBuilder
	function reloadAffectedEndItemsTable(param){
		// 获取原有数据
		var affectedProductID = eval("("+document.getElementById("affectedProductID").value+")");
		// 输入参数转换JSON
		var addJson = eval("("+param+")");
		for(var i=0; i < addJson.length; i++){
			if(affectedProductID.indexOf(addJson[i]) == -1){
				affectedProductID[affectedProductID.length] = addJson[i] ;
			}
        }
		document.getElementById("affectedProductID").value = JSON.stringify(affectedProductID) ;
		PTC.jca.table.Utils.reload('ext.appo.ecn.mvc.builder.AffectedEndItemsTableBuilder',{affectedProductID:JSON.stringify(affectedProductID)},true);
	}
   
    function ajaxRequest(url, params) {
		var options = {
			asynchronous : false,
			parameters : params,
			method : 'POST'
		};
		var transport = requestHandler.doRequest(url, options);
		return transport.responseText ;
	}

	// 保存受影响列表客制化字段信息
	function saveChangeTaskArray(){
		// 用于存储所有数据
		var tableRowArry=[];
		// 获取受影响对象表单中的所有数据
		var tableRows = PTC.jca.table.Utils.getTableRows("changeTask_affectedItems_table").items;
		for(var i = 0; i < tableRows.length; i++){
            var tableRow = tableRows[i].data ;
			var columnArray = {} ;
			columnArray['oid'] = tableRow.oid ;
			columnArray['aadDescription'] = tableRow.aadDescription.gui.comparable ;
			if(tableRow.hasOwnProperty('IBA|ArticleInventory')){
				columnArray['ArticleInventory'] = tableRow['IBA|ArticleInventory'].gui.comparable ;
			}else{
				columnArray['ArticleInventory'] = '' ;
			}
			if(tableRow.hasOwnProperty('IBA|CentralWarehouseInventory')){
				columnArray['CentralWarehouseInventory'] = tableRow['IBA|CentralWarehouseInventory'].gui.comparable ;
			}else{
				columnArray['CentralWarehouseInventory'] = '' ;
			}
			if(tableRow.hasOwnProperty('IBA|PassageInventory')){
				columnArray['PassageInventory'] = tableRow['IBA|PassageInventory'].gui.comparable ;
			}else{
				columnArray['PassageInventory'] = '' ;
			}
			if(tableRow.hasOwnProperty('IBA|ResponsiblePerson')){
				columnArray['ResponsiblePerson'] = tableRow['IBA|ResponsiblePerson'].gui.comparable ;
			}else{
				columnArray['ResponsiblePerson'] = '' ;
			}
			if(tableRow.hasOwnProperty('IBA|ArticleDispose')){
				var articleDispose = tableRow['IBA|ArticleDispose'].gui.comparable ;
				if(articleDispose.indexOf('[') > -1){
					columnArray['ArticleDispose'] = articleDispose.substring(articleDispose.lastIndexOf('[') + 1, articleDispose.length - 1) ;
				}else{
					columnArray['ArticleDispose'] = articleDispose;
				}
			}else{
				columnArray['ArticleDispose'] = '' ;
			}
			if(tableRow.hasOwnProperty('IBA|PassageDispose')){
				var passageDispose = tableRow['IBA|PassageDispose'].gui.comparable
				if(passageDispose.indexOf('[') > -1){
					columnArray['PassageDispose'] = passageDispose.substring(passageDispose.lastIndexOf('[') + 1, passageDispose.length - 1) ;
				}else{
					columnArray['PassageDispose'] = passageDispose ;
				}
			}else{
				columnArray['PassageDispose'] = '' ;
			}
			if(tableRow.hasOwnProperty('IBA|InventoryDispose')){
				var inventoryDispose = tableRow['IBA|InventoryDispose'].gui.comparable ;
				if(inventoryDispose.indexOf('[') > -1){
					columnArray['InventoryDispose'] =  inventoryDispose.substring(inventoryDispose.lastIndexOf('[') + 1, inventoryDispose.length - 1) ;
				}else{
					columnArray['InventoryDispose'] =  inventoryDispose ;
				}
			}else{
				columnArray['InventoryDispose'] = '' ;
			}
			if(tableRow.hasOwnProperty('IBA|CompletionTime')){
				columnArray['CompletionTime'] = tableRow['IBA|CompletionTime'].gui.comparable ;
			}else{
				columnArray['CompletionTime'] = '' ;
			}
			if(tableRow.hasOwnProperty('IBA|ChangeType')){
				var changeType = tableRow['IBA|ChangeType'].gui.comparable ;
				if(changeType.indexOf('[') > -1){
					columnArray['ChangeType'] = changeType.substring(changeType.lastIndexOf('[') + 1, changeType.length - 1) ;
				}else{
					columnArray['ChangeType'] = changeType ;
				}
			}else{
				columnArray['ChangeType'] = '' ;
			}
			if(tableRow.hasOwnProperty('IBA|ProductDispose')){
				var productDispose = tableRow['IBA|ProductDispose'].gui.comparable ;
				if(productDispose.indexOf('[') > -1){
					columnArray['ProductDispose'] = productDispose.substring(productDispose.lastIndexOf('[') + 1, productDispose.length - 1) ;
				}else{
					columnArray['ProductDispose'] = productDispose ;
				}
			}else{
				columnArray['ProductDispose'] = '' ;
			}
			tableRowArry[i] = columnArray ;
        }
		document.getElementById("changeTaskArray").value = JSON.stringify(tableRowArry) ;
	}
	
	// 保存受影响产品表单中所有数据
	function saveAffectedEndItemsTable(){
		var affectedProductID = [] ;
		// 获取受影响对象表单中的所有数据
		var tableRows = PTC.jca.table.Utils.getTableRows("ext.appo.ecn.mvc.builder.AffectedEndItemsTableBuilder").items;
		for(var i = 0; i < tableRows.length; i++){
			affectedProductID[i] = tableRows[i].data.oid ;
		}
		document.getElementById("affectedProductID").value = JSON.stringify(affectedProductID) ;
	}
	
	// 保存事务性任务表单数据
	function saveDatasArray(){
		// 获取表单中所有存储的数据
		var allDatas = {} ;
		var grid = window.PTC.jca.table.Utils.getTable(table_id);
		var store = grid.getStore();
		store.each(function(record){
			var json = {};
			if(record.data.changeTheme){
				json['changeTheme'] = record.data.changeTheme;
			}
			if(record.data.changeDescribe){
				json['changeDescribe'] = record.data.changeDescribe;
			}
			if(record.data.responsible){
				json['responsible'] = record.data.responsible;
			}
			if(record.data.changeActivity2){
				json['changeActivity2'] = record.data.changeActivity2;
			}
			allDatas[record.data.oid] = json ;
		});
		document.getElementById("datasArray").value = JSON.stringify(allDatas) ;
	}
	
	PTC.onReady(function() {
		setTimeout("saveAffectedEndItemsTable()",1000);

		setTimeout("saveDatasArray()",1000);
	});
</script>

<%@ include file="/netmarkets/jsp/util/end.jspf"%>