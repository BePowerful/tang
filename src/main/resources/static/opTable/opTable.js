/**
 @ Name：表格冗余列可展开显示
 @ Author：hbm
 @ License：MIT
 */

layui.define(['form', 'table'], function (exports) {
  var $ = layui.$
      , table = layui.table
      , form = layui.form
      , VERSION = 1.0, MOD_NAME = 'opTable'
      // 展开 , 关闭
      , ON = 'on', OFF = 'off', KEY_STATUS = "status"
      // openType 0、默认效果同时只展开一项  1、点击展开多项 2、 展开全部  3、关闭全部
      , OPEN_DEF = 0, OPEN_NO_CLOSE = 1, OPEN_ALL = 2, CLOSE_ALL = 3
      // 外部接口
      , opTable = {
        index: layui.opTable ? (layui.opTable.index + 10000) : 0
        // 设置全局项
        , set: function (options) {
          var that = this;
          that.config = $.extend({}, that.config, options);
          return that;
        }

        // 事件监听
        //, on: function (events, callback) {
        //  return layui.onevent.call(this, MOD_NAME, events, callback);
        //}
      }
      // 展开列需要需要显示的数据 数据格式为 每个页面唯一的（LAY_IINDEX）下标绑定数据  对应的数据
      , openItemData = {}
      , getOpenClickClass = function (elem, isAddClickClass) {
        return elem.replace("#", '').replace(".", '') + (isAddClickClass ? 'opTable-i-table-open' : '')
      }
      , getOpenAllClickClass = function (elem) {
        return getOpenClickClass(elem, true) + "-all"
      }
      // 操作当前实例
      , thisIns = function () {
        var that = this
            , options = that.config
            , id = options.id || options.index;

        return {
          reload: function (options) {
            that.config = $.extend(that.config, options);
            that.config.cols[0].splice(0, 1);
            that.render();
            return this;
          }
          , config: options

          // 展开全部
          , openAll: function () {
            // 表格 同时只支持展开一项
            if (that.config.openTable || this.isOpenAll()) {
              return this;
            }

            var def = that.config.openType;
            that.config.openType = OPEN_ALL;
            $("." + getOpenClickClass(that.config.elem, true)).parent().click();
            that.config.openType = def;

            $("." + getOpenAllClickClass(that.config.elem))
                .addClass("opTable-open-dow")
                .removeClass("opTable-open-up")
                .attr(KEY_STATUS, ON);

            that.config.onOpenAll && that.config.onOpenAll();
            return this;
          }
          // 关闭全部
          , closeAll: function () {
            if (!this.isOpenAll()) {
              return this;
            }

            var def = that.config.openType;
            that.config.openType = CLOSE_ALL;
            $("." + getOpenClickClass(that.config.elem, true)).parent().click();
            that.config.openType = def;

            $("." + getOpenAllClickClass(that.config.elem))
                .addClass("opTable-open-up")
                .removeClass("opTable-open-dow")
                .attr(KEY_STATUS, OFF);

            that.config.onCloseAll && that.config.onCloseAll();
            return this;
          }

          // 通过下标展开一项
          , openIndex: function (index) {
            var dom = $("." + getOpenClickClass(that.config.elem, true)).eq(index);
            if (dom.length <= 0) {
              return false;
            }
            var status = dom.attr(KEY_STATUS);
            if (status === ON) {
              return true;
            }
            dom.click();
            return true;
          }
          // 通过下标展开一项
          , closeIndex: function (index) {
            var dom = $("." + getOpenClickClass(that.config.elem, true)).eq(index);
            if (dom.length <= 0) {
              return false;
            }
            var status = dom.attr(KEY_STATUS);
            if (status === OFF) {
              return true;
            }
            dom.click();
            return true;
          }
          // 当前展开状态取反
          , toggleOpenIndex: function (index) {
            var dom = $("." + getOpenClickClass(that.config.elem, true)).eq(index);
            if (dom.length <= 0) {
              return false;
            }
            dom.parent().click();
            return true;
          }
          // 当前是否全部展开
          , isOpenAll: function () {
            var localTag = $("." + getOpenClickClass(that.config.elem, true));
            var allTag = localTag.length;
            var openTag = localTag.parent().parent().parent().parent().find(".opTable-open-dow").length;
            // 当所有的选项都被展开 则 true
            return allTag === openTag;
          }
        }
      }

      //构造器
      , Class = function (options) {
        var that = this;
        that.index = ++opTable.index;
        that.config = $.extend({}, that.config, opTable.config, options);
        that.render();
        return this;
      };

  //默认配置
  Class.prototype.config = {
    openType: OPEN_DEF
    // 展开的item (垂直v|水平h) 排序
    , opOrientation: 'v'
    // layui table 对象
    , table: null
    , childTable: null
    // 展开动画执行时长
    , slideDownTime: 200
    // 关闭动画执行时长
    , slideUpTime: 100
  };

  //渲染视图
  Class.prototype.render = function () {
    var that = this
        , options = that.config
        , colArr = options.cols[0]
        , openCols = options.openCols || []
        , openNetwork = options.openNetwork || null
        , openTable = options.openTable || null
        , done = options.done;


    // 展开显示表格 同时只支持展开一个
    options.openType = openTable ? OPEN_DEF : options.openType;

    delete options["done"];
    //  1、在第一列 插入可展开操作
    colArr.splice(0, 0, {
      align: 'left',
      width: 50,
      title: openTable ? ''
          : '<i class="opTable-i-table-open ' + getOpenAllClickClass(options.elem) + ' " ' + KEY_STATUS + '="off"  title="(展开|关闭)全部"></i>',
      templet: function (item) {
        // 解决页面多个表格问题
        var cla = getOpenClickClass(options.elem, false);

        openItemData[cla] = openItemData[cla] || {};
        openItemData[cla][item.LAY_INDEX] = item;
        return "<i class='opTable-i-table-open " + cla + "opTable-i-table-open' " + KEY_STATUS + "='off'  data='"
            //  把当前列的数据绑定到控件
            + item.LAY_INDEX
            + " ' elem='"
            + cla
            + "' title='展开'></i>";
      }
    });

    //  2、表格Render
    options.table = table.render(
        $.extend({
          done: function (res, curr, count) {
            initExpandedListener();
            if (done) {
              done(res, curr, count)
            }
          }
        }, options));


    // 3、展开事件
    function initExpandedListener() {
      $("." + getOpenClickClass(options.elem, true))
          .parent()
          .unbind("click")
          .click(function () {
            var that = $(this).children()
                , _this = this
                , itemIndex = parseInt(that.attr("data"))
                , bindOpenData = openItemData[that.attr("elem")][itemIndex]
                , status = that.attr(KEY_STATUS) === 'on'
                // 操作倒三角
                , dowDom = that.parent().parent().parent().parent().find(".opTable-open-dow")
                // 展开的tr
                , addTD = that.parent().parent().parent().parent().find(".opTable-open-td"),
                // 行点击Class
                itemClickClass = options.elem.replace("#", '').replace(".", '') + '-opTable-open-item-div';

            function initOnClose() {
              options.onClose && options.onClose(bindOpenData, itemIndex)
              options.childTable = null;
            }

            // 关闭全部
            if (options.openType === CLOSE_ALL) {
              dowDom
                  .addClass("opTable-open-up")
                  .removeClass("opTable-open-dow")
                  .attr(KEY_STATUS, OFF);
              addTD.slideUp(options.slideUpTime, function () {
                addTD.remove();
              });
              //关闭回调
              initOnClose();
              return;
            }

            // 展开全部
            if (options.openType === OPEN_ALL) {
              dowDom
                  .addClass("opTable-open-dow")
                  .removeClass("opTable-open-up")
                  .attr(KEY_STATUS, ON);
              if (status) {
                _this.addTR.remove();
              }
            } else if (options.openType === OPEN_DEF) {
              // 关闭类型
              var sta = dowDom.attr(KEY_STATUS),
                  isThis = (that.attr("data") === dowDom.attr("data"));
              //1、关闭展开的
              dowDom
                  .addClass("opTable-open-up")
                  .removeClass("opTable-open-dow")
                  .attr(KEY_STATUS, OFF);

              //2、如果当前 = 展开 && 不等于当前的 关闭
              if (sta === ON && isThis) {
                addTD.slideUp(options.slideUpTime, function () {
                  addTD.remove();
                  initOnClose();
                });
                return;
              } else {
                that.attr(KEY_STATUS, OFF);
                addTD.remove();
              }
            } else if (options.openType === OPEN_NO_CLOSE) {
              //  1、如果当前为打开，再次点击则关闭
              if (status) {
                that.removeClass("opTable-open-dow");
                that.attr(KEY_STATUS, OFF);
                this.addTR.find("div").eq(0).slideUp(options.slideUpTime, function () {
                  _this.addTR.remove();
                  //关闭回调
                  initOnClose();
                });
                return;
              }

            }

            // 把添加的 tr 绑定到当前 移除时使用
            this.addTR = $([
              "<tr><td class='opTable-open-td'  colspan='" + (colArr.length + 1) + "'>"
              , "<div style='margin-left: 50px;display: none'></div>"
              , "</td></tr>"].join("")
            );

            // 所有内容的主容器
            var divContent =
                _this.addTR
                    .children()
                    .children();

            that.parent().parent().parent().after(this.addTR);

            var html = [];

            // 1、从网络获取
            if (openNetwork) {
              loadNetwork();
            } else if (openTable) {
              console.log(openTable)
              if (typeof openTable !== "function") {
                throw  "OPTable: openTable attribute is function ";
              }

              var tableOptions = openTable(bindOpenData);
              var id = tableOptions.elem.replace("#", '').replace(".", '');
              //2、展开显示表格
              divContent
                  .empty()
                  .append("<table id='" + id + "' lay-filter='" + id + "'></table>")
                  .css({
                    "padding": "0 10px 0 50px", "margin-left": "0", "width":
                        _this.addTR.width()
                  })
                  .fadeIn(400);

              // 设置展开表格颜色为浅色背景
              addTD.css("cssText", "background-color:#FCFCFC!important");

              options.childTable = layui.table.render(tableOptions);
            } else {
              //  3、从左到右依次排列 Item 默认风格
              openCols.forEach(function (val, index) {
                appendItem(val, bindOpenData);
              });
              divContent.append(html.join(''));
              this.addTR.find("div").slideDown(options.slideDownTime);
              bindBlur(bindOpenData);
            }

            function loadNetwork() {
              divContent.empty()
                  .append('<div class="opTable-network-message" ><i class="layui-icon layui-icon-loading layui-icon layui-anim layui-anim-rotate layui-anim-loop" data-anim="layui-anim-rotate layui-anim-loop"></i></div>');
              _this.addTR.find("div").slideDown(options.slideDownTime);


              openNetwork.onNetwork(bindOpenData
                  //加载成功
                  , function (obj) {
                    //  2、从左到右依次排列 Item
                    openNetwork.openCols.forEach(function (val, index) {
                      appendItem(val, obj);
                    });
                    // 填充展开数据
                    divContent.empty().append(html.join(''));
                    bindBlur(obj);
                  }
                  , function (msg) {
                    divContent.empty()
                        .append("<div class='opTable-reload opTable-network-message' style='text-align: center;margin-top: 20px'>" + (msg || "没有数据") + "</div>")

                    $(".opTable-reload")
                        .unbind()
                        .click(function (e) {
                          loadNetwork();
                        });
                  })
            }


            /**
             * 添加默认排版风格 item
             * @param colsItem  cols配置信息
             * @param openData  展开数据
             */
            function appendItem(colsItem, openData) {
              //  1、自定义模板
              if (colsItem.templet) {
                html.push("<div id='" + colsItem.field + "' class='opTable-open-item-div " + itemClickClass + "' opOrientation='" + options.opOrientation + "'>")
                html.push(colsItem.templet(openData));
                html.push("</div>")
                //  2、可下拉选择类型
              } else if (colsItem.type && colsItem.type === 'select') {
                var child = ["<div id='" + colsItem.field + "' class='opTable-open-item-div " + itemClickClass + "' opOrientation='" + options.opOrientation + "' >"];
                child.push("<span style='color: #99a9bf'>" + colsItem["title"] + "：</span>");
                child.push("<div class='layui-input-inline'><select  lay-filter='" + colsItem.field + "'>");
                colsItem.items.forEach(function (it) {
                  it = colsItem.onDraw(it, openData);
                  child.push("<option value='" + it.id + "' ");
                  child.push(it.isSelect ? " selected='selected' " : "");
                  child.push(" >" + it.value + "</option>");
                });
                child.push("</select></div>");
                child.push("</div>");
                html.push(child.join(""));
                setTimeout(function () {
                  layui.form.render();
                  //  监听 select 修改
                  layui.form.on('select(' + colsItem.field + ')', function (data) {

                    if (options.onEdit && colsItem.isEdit(data, openData)) {
                      var json = {};
                      json.value = data.value;
                      json.field = colsItem.field;
                      openData[colsItem.field] = data.value;
                      json.data = JSON.parse(JSON.stringify(openData));
                      options.onEdit(json);
                    }
                  });
                }, 20);
              } else {
                var text = colsItem.onDraw ? colsItem.onDraw(openData) : openData[colsItem["field"]];
                // 3、默认类型
                html.push("<div id='" + colsItem.field + "' class='opTable-open-item-div " + itemClickClass + "' opOrientation='" + options.opOrientation + "'>");
                html.push("<span class='opTable-item-title'>" + colsItem["title"] + "：</span>");
                html.push((colsItem.onEdit ?
                        ("<input  class='opTable-exp-value opTable-exp-value-edit' autocomplete='off' name='" + colsItem["field"] + "' value='" + text + "'/>")
                        : ("<span class='opTable-exp-value' >" + text + "</span>")
                ));
                html.push("</div>");
              }

            }

            /**
             * 绑定监听 修改失焦点监听
             * @param bindOpenData
             */
            function bindBlur(bindOpenData) {
              $(".opTable-exp-value-edit")
                  .unbind("blur")
                  .blur(function () {
                    var that = $(this), name = that.attr("name"), val = that.val();
                    // 设置了回调 &&发生了修改
                    if (options.onEdit && bindOpenData[name] + "" !== val) {
                      var json = {};
                      json.value = that.val();
                      json.field = that.attr("name");
                      bindOpenData[name] = val;
                      json.data = bindOpenData;
                      options.onEdit(json);
                    }
                  })
                  .keypress(function (even) {
                    even.which === 13 && $(this).blur()
                  })
            }


            if (options.onItemClick) {
              $("." + itemClickClass)
                  .unbind("click")
                  .click(function (e) {
                    var field = $(this).attr("id");
                    options.onItemClick({
                      lineData: bindOpenData,
                      field: field,
                      value: bindOpenData[field],
                      div: $(this),
                      e: e
                    });
                  });
            }

            that.addClass("opTable-open-dow");
            that.attr(KEY_STATUS, ON);

            // 创建成功回调
            options.onInitSuccess && options.onInitSuccess(bindOpenData, itemIndex, this.addTR);
            setTimeout(function () {
              // 展开回调
              options.onOpen && options.onOpen(bindOpenData, itemIndex, this.addTR);
            }, options.slideDownTime);

          });


      // (展开|关闭)全部
      $("." + getOpenAllClickClass(options.elem))
          .parent()
          .parent()
          .unbind("click")
          .click(function () {
            var tag = $(this).find("i"), status = tag.attr(KEY_STATUS);
            if (status === ON) {
              tag.addClass("opTable-open-up")
                  .removeClass("opTable-open-dow")
                  .attr(KEY_STATUS, OFF);
              options.thisIns.closeAll();
            } else {
              tag
                  .addClass("opTable-open-dow")
                  .removeClass("opTable-open-up")
                  .attr(KEY_STATUS, ON);
              options.thisIns.openAll();
            }

          })
    }

    //  4、监听排序事件
    var elem = $(options.elem).attr("lay-filter");

    //  5、监听表格排序
    table.on('sort(' + elem + ')', function (obj) {
      if (options.onSort) {
        options.onSort(obj)
      }
      // 重新绑定事件
      initExpandedListener();
    });

    //  6、单元格编辑
    layui.table.on('edit(' + elem + ')', function (obj) {
      if (options.onEdit) {
        options.onEdit(obj)
      }
    });

  };

  //核心入口
  opTable.render = function (options) {
    var ins = new Class(options);
    var ex = thisIns.call(ins);
    ins.config.thisIns = ex;
    return ex;
  };

  //加载组件所需样式
  layui.link(layui.cache.base + '/opTable.css?v=1' + VERSION, function () {
    //此处的“opTable”要对应 opTable.css 中的样式： html #layuicss-opTable{}
  }, 'opTable');

  exports('opTable', opTable);

});
