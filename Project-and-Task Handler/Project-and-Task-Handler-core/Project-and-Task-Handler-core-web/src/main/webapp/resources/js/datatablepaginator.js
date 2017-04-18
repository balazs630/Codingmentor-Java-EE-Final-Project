$(function () {
    if (typeof fireRowsChange !== "function") {
        return;
    }
    var dataTableWidget = PF("dataTableWidget");
    if (!dataTableWidget) {
        return;
    }
    var paginator = dataTableWidget.getPaginator();
    var selects = paginator.getJQ().find("select");
    selects.change(function (e) {
        var newRows = paginator.cfg.rows;
        selects.val(newRows);
        fireRowsChange([{name: "newRows", value: newRows}]);
    });
});


