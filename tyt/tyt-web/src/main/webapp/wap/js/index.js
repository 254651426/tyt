;(function (exports) {
    var KeyBoard = function (input, options) {
        var body = document.getElementsByTagName('body')[0];
        var DIV_ID = options && options.divId || 'calculator';

        if (document.getElementById(DIV_ID)) {
            document.getElementById("inputContent").removeChild(document.getElementById(DIV_ID));
        }

        this.input = input;
        this.el = document.createElement('div');
        var self = this;
        var TABLE_ID = options && options.table_id || 'calculatorTable';
        var mobile = typeof orientation !== 'undefined';
        this.el.id = DIV_ID;

        var cssStr = '<style type="text/css">';
        cssStr += '#' + TABLE_ID + '{text-align:center;width:100%;height:160px;background-color:#FFF;margin-top: 10px;border-top:1px solid #ddd;border-right:1px solid #ddd;}';
        cssStr += '#' + TABLE_ID + ' td{width:30%;border:1px solid #ddd;border-right:0;border-top:0;}';
        if (!mobile) {
            cssStr += '#' + TABLE_ID + ' td:hover{background-color:#1FB9FF;color:#FFF;}';
        }
        cssStr += '</style>';

        var tableStr = '<table id="' + TABLE_ID + '" border="0" cellspacing="0" cellpadding="0">';
        tableStr += '<tr><td>1</td><td>2</td><td>3</td><td style="background-color:#D3D9DF;">删除</td></tr>';
        tableStr += '<tr><td>4</td><td>5</td><td>6</td><td rowspan="3"></td></tr>';
        tableStr += '<tr><td>7</td><td>8</td><td>9</td></tr>';
        tableStr += '<tr><td colspan="2">0</td><td>.</td>';
        tableStr += '</table>';
        this.el.innerHTML = cssStr + tableStr;

        function addEvent(e) {
            var ev = e || window.event;
            var clickEl = ev.element || ev.target;
            var num = "";
            var value = clickEl.textContent || clickEl.innerText;
            if (clickEl.tagName.toLocaleLowerCase() === 'td' && value !== "删除" && value !== "清空") {
                if (self.input) {
                    self.input.value += value;
                }
            } else if (clickEl.tagName.toLocaleLowerCase() === 'td' && value === "删除") {
                //num = self.input.value;
                if (num) {
                    var newNum = num.substr(0, num.length-1);
                    self.input.value = newNum;
                }
            } else if (clickEl.tagName.toLocaleLowerCase() === 'td' && value === "清空") {
                //num = self.input.value;
                if (num) {
                    self.input.value = "";
                }
            }
        }
        if (mobile) {
            this.el.ontouchstart = addEvent;
        } else {
            this.el.onclick = addEvent;
        }
        document.getElementById("inputContent").appendChild(this.el);
    };
    exports.KeyBoard = KeyBoard;

})(window);