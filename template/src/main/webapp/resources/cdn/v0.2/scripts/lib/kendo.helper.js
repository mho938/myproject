var mode = '';
var canEdit = new Object();
var index = 0;

/*
 * $.validator.setDefaults({ ignore: "" });
 */

function getIndex() {
	return ++index;
}
function setStartOnDataBinding() {
	index = (this.dataSource.page() - 1) * this.dataSource.pageSize();
}

function setKeyDown(gridName) {
	$(document.body).keydown(function(e) {
		if (e.altKey && e.keyCode == 87) {
			$("#" + gridName).data("kendoGrid").table.focus();
		}
	});
}

function handleDelete(current) {
	$(current).parent().parent().find('.k-grid-Delete').trigger('click')
}

function _handleDelete(gridName, event) {
	mode = 'delete';

	var dataitem = {};
	var grid = $("#" + gridName).data('kendoGrid');

	dataitem = grid.dataItem($(event.currentTarget).closest('tr'));
	kendo.showYesNoBox({
		title : dictionary.delete_record,
		message : dictionary.delete_confirm,
		done : function(response) {
			if (response.button == "Yes") {
				grid.dataSource.remove(dataitem);
				grid.dataSource.sync();
				if (grid._data.length == 0)
					grid.dataSource.read();
			} else {
				dataitem = {};
			}
		}
	});
}

function _openModal(e) {
	mode = 'change';

	var title;
	if (e.model.isNew()) {
		// create
		title = dictionary.add;
	} else {
		// edit
		title = dictionary.edit;
	}

	var wnd = e.container.data("kendoWindow");
	wnd.title(title);

	setTimeout(function() {
		$('.k-window input[type=text]:eq(0)').select().focus();
	}, 500);

	if (typeof (e.model.Readonly) != "undefined") {
		isReadonly = e.model.Readonly;
		if (isReadonly) {
			setReadonly();
		}
	}
}

function _dataBound() {
	canEdit = new Object();
}

function _gridDetailInit(e) {
	if (typeof (e.data.Readonly) != "undefined") {
		var editable = (e.data.Readonly == false);
		canEdit[e.data.ID] = editable;
		if (!editable) {
			$(e.detailRow).find('.k-toolbar').remove();
		}
	}

	$(e.detailRow).find('.k-grid-header-wrap').scrollLeft(0);
}

function _cancelModal(e) {
	if (e.model.Readonly == true) {
		window.setTimeout(function() {
			if (e.model.get('Readonly') == false)
				e.model.set('Readonly', true);
		}, 500);
	}
}

function _saveModal(gridName, e) {
	window.setTimeout(function() {
		if (!hasError) {
			var grid = $("#" + gridName).data("kendoGrid");
			grid.dataSource.read();
		}
	}, 1000);
}

var hasError = false;
function error_handler(gridName, e) {
	hasError = false;
	if (e.errors) {
		hasError = true;
		var grid = $("#" + gridName).data("kendoGrid");
		if (mode == 'delete')
			grid.dataSource.cancelChanges();
		grid.one("dataBinding", function(e) {
			e.preventDefault(); // cancel grid rebind if error occurs
		});
		var message = "";
		var i = 0;
		$.each(e.errors, function(key, value) {
			if ('errors' in value) {
				$.each(value.errors, function() {
					if (i < 3)
						// message += this;
						message += this + "<br/>";
					i = i + 1;
				});
			}
		});
		kendo.showErrorBox(message);
	} else {
		switch (e.xhr.status) {
		case 401:
			kendo.showErrorBox(dictionary.Unauthorized);
			break;
		case 405:
			kendo.showErrorBox(dictionary.AccessDenied);
			var grid = $("#" + gridName).data("kendoGrid");
			grid.dataSource.read();
			break;
		default:
			kendo.showErrorBox(e.errorThrown);
		}
	}
}


function _dataBoundSubentity(e, masterID) {
	if (typeof (canEdit[masterID]) != "undefined" && !canEdit[masterID]) {
		var cols = $('#grid_' + masterID).data('kendoGrid').columns;
		var commandIndex = -1;
		for (i = 0; i < cols.length; i++) {
			if (typeof (cols[i].command) != 'undefined')
				commandIndex = i;
		}

		if (commandIndex > 0) {
			$(
					'#grid_' + masterID + ' .k-grid-header table th:eq('
							+ commandIndex + ')').remove();
			$('#grid_' + masterID + ' .k-grid-content table tr').each(
					function() {
						$(this).find('td:eq(' + commandIndex + ')').remove();
					});
		} else if (commandIndex == 0 && cols[0].locked == true) {
			if ($('#grid_' + masterID + ' .k-grid-header .k-grid-header-locked').length > 0) {
				$('#grid_' + masterID + ' .k-grid-header .k-grid-header-locked')
						.remove()
				// var width1 = parseInt($('#grid_' + masterID + '
				// .k-grid-content-locked').css('width'), 10);
				// var width2 = parseInt($('#grid_' + masterID + '
				// .k-grid-content').css('width'), 10);
				// $('#grid_' + masterID + ' .k-grid-content').css('width',
				// (width1 + width2) + "px");
				$('#grid_' + masterID + ' .k-grid-content-locked').remove();
			}
		}
	}
}

function onDataBoundCorrectLink(gridName, e) {
	var grid = $('#' + gridName).data('kendoGrid');

	var requestObject = (new kendo.data.transports["aspnetmvc-server"]({
		prefix : ""
	})).options.parameterMap({
		page : grid.dataSource.page(),
		sort : grid.dataSource.sort(),
		filter : grid.dataSource.filter()
	});

	$(".export").each(
			function() {
				// Get the export link as jQuery object
				var $exportLink = $(this);

				// Get its 'href' attribute - the URL where it would navigate to
				var href = $exportLink.attr('href');

				// Update the 'page' parameter with the grid's current page
				// href = href.replace(/page=([^&]*)/, 'page=' +
				// requestObject.page || '~');

				// Update the 'sort' parameter with the grid's current sort
				// descriptor
				href = href.replace(/sort=([^&]*)/, 'sort='
						+ requestObject.sort || '~');

				// Update the 'pageSize' parameter with the grid's current
				// pageSize
				// href = href.replace(/pageSize=([^&]*)/, 'pageSize=' +
				// grid.dataSource.total());

				// update filter descriptor with the filters applied

				href = href.replace(/filter=([^&]*)/, 'filter='
						+ (requestObject.filter || '~'));

				// Update the 'href' attribute
				$exportLink.attr('href', href);
			})
}
