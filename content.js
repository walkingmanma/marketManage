//创建DTO commodityclass Model-------------------------------------------------------------------
Ext.define
('CommodityClass',
	{
		extend:'Ext.data.Model',
		fields:
		[
			{name:'typeid',type:'int'},
			{name:'typename',type:'string'},
			{name:'typedesc',type:'string'},
		]
	}
);

Ext.define
('Commodity',
 	{	
		extend:'Ext.data.Model',
		fields:
		[
			{name:'commodityid',type:'int'},
			{name:'typename',type:'string'},
			{name:'typeid',type:'int'},
			{name:'commodityname',type:'string'},
			{name:'manufacture',type:'string'},
			{name:'commoditydesc',type:'string'},
			{name:'commodityprice',type:'float'},
			{name:'fcprice',type:'float'},
			{name:'commodityamount',type:'int'},
			{name:'commodityleavenum',type:'int'},
			{name:'tagtime',type:'date'}	
		]
	}
);

//创建一个DTO user Model
Ext.define
( 'User',
	{
		extend:'Ext.data.Model',
		fields:
		[
			{name:'userid',type:'int'},
			{name:'username',type:'string'},
			{name:'password',type:'string'},
			{name:'name',type:'string'},
			{name:'sex',type:'string'},
			{name:'address',type:'string'},
			{name:'phone',type:'string'},
			{name:'email',type:'string '},
			{name:'tagtime',type:'date'},
			{name:'postaddress',type:'string'}		
		]
	}
);

//创建一个DTO admin Model
Ext.define
( 'Admin',
	{
		extend:'Ext.data.Model',
		fields:
		[
			{name:'adminid',type:'int'},
			{name:'username',type:'string'},
			{name:'password',type:'string'},
			{name:'sex',type:'string'},
			{name:'hireday',type:'date'},	
		]
	}
);


//创建一个DTO Orderform Model
Ext.define
( 'OrderForm',
	{
		extend:'Ext.data.Model',
		fields:
		[
			{name:'orderformid',type:'int'},
			{name:'name',type:'string'},
			{name:'submittime',type:'date'},
			{name:'totalprice',type:'float'},
			{name:'ispayoff',type:'float'},	
		]
	}
);

//创建一个DTO OrderList Model
Ext.define
( 'OrderList',
	{
		extend:'Ext.data.Model',
		fields:
		[
			{name:'orderlistid',type:'int'},
			{name:'commodityname',type:'string'},
			{name:'commodityid',type:'int'},
			{name:'commodityprice',type:'float'},
			{name:'orderformid',type:'int'},
			{name:'amount',type:'int'},
		]
	}
);
//创建一个Ext.data.Store对象用于管理CommodityClass Model ------------------------------------------------------------------
var commodityClassStore=Ext.create
(	'Ext.data.Store',
	{
		model:'CommodityClass',
		pageSize:50,
		sorters:[{property:'typeid',direction:'asc'}],
        autoSync: true,
        proxy: 
        {
            type: 'ajax',
            api: 
            {
                read: 'typeDeal',
                create: 'addtype',
                update: 'update',
                destroy: 'deletetype'
            },
            reader: 
            {
                type: 'json',
                successProperty: 'success',
                root: 'data',
                messageProperty: 'msg'
            },
            writer: 
            {
                type: 'json',
                writeAllFields: true,
                nameProperty: 'mapping'
            },
            listeners: 
            {
                exception: function(proxy, response, operation)
                {
                    Ext.MessageBox.show(
                    {
                        title: '服务器异常',
                        msg: operation.getError(),
                        icon: Ext.MessageBox.ERROR,
                        buttons: Ext.Msg.OK
                    });
                }
            }
        },
        listeners: 
        {
            write: function(store, operation)
            {
                Ext.MessageBox.show(
         			   {
         					 title: operation.action,
         					 msg:operation.resultSet.message,
                             buttons: Ext.Msg.OK
         			   });
            }
        }
	}
);

//创建一个store用于存储CommodityClass,但采用不同的url---------------------------------------------------------
var commodityClassStore2=Ext.create
(	'Ext.data.Store',
	{
		model:'CommodityClass',
		sorters:[{property:'typeid',direction:'asc'}],
		pageSize:20,
        autoSync: true,
        proxy: 
        {
            type: 'ajax',
    		getMethod: function(){ return 'POST'; },
            api: 
            {
                read: 'typeDealByName',
                create: 'addtype',
                update: 'update',
                destroy: 'deletetype'
            },
            reader: 
            {
                type: 'json',
                successProperty: 'success',
                root: 'data',
                messageProperty: 'msg'
            },
            writer: 
            {
                type: 'json',
                writeAllFields: true,
            },
            listeners: 
            {
                exception: function(proxy, response, operation)
                {
                    Ext.MessageBox.show(
                    {
                        title: '查询情况',
                        msg: operation.getError(),
                        icon: Ext.MessageBox.ERROR,
                        buttons: Ext.Msg.OK
                    });
                }
            }
        },
        listeners: 
        {
            write: function(proxy, operation)
            {
                Ext.MessageBox.show(
          			   {
          					 title: operation.action,
          					 msg: operation.resultSet.message,
                             buttons: Ext.Msg.OK
          			   });
            }
        }
	}
);

//创建一个store用于存储Commodity
var commodityStore=Ext.create
(	'Ext.data.Store',
	{
		model:'Commodity',
		pageSize:50,
        proxy: 
        {
            type: 'ajax',
			url:'findcommodity',
    		getMethod: function(){ return 'POST'; },	
            reader: 
            {
                type: 'json',
                successProperty: 'success',
                root: 'data',
                messageProperty: 'msg'
            },
            writer: 
            {
                type: 'json',
                writeAllFields: true,
            },
            listeners: 
            {
                exception: function(proxy, response, operation)
                {
                    Ext.MessageBox.show(
                    {
                        title: '服务器异常',
                        msg: operation.getError(),
                        icon: Ext.MessageBox.ERROR,
                        buttons: Ext.Msg.OK
                    });
                }
            }
        },
        listeners: 
        {
            write: function(proxy, operation)
            {
                if (operation.action == 'destroy') 
                {
                    main.child('#form').setActiveRecord(null);
                }
                Ext.MessageBox.show(
          			   {
          					 title: operation.action,
          					 msg: operation.resultSet.message,
                             buttons: Ext.Msg.OK
          			   });
            }
        }
	}
);

//创建一个store用于存储User
var userStore=Ext.create
(	'Ext.data.Store',
	{
		model:'User',
		pageSize:50,
        proxy: 
        {
            type: 'ajax',
			url:'finduser',
    		getMethod: function(){ return 'POST'; },	
            reader: 
            {
                type: 'json',
                successProperty: 'success',
                root: 'data',
                messageProperty: 'msg'
            },
            writer: 
            {
                type: 'json',
                writeAllFields: true,
            },
            listeners: 
            {
                exception: function(proxy, response, operation)
                {
                    Ext.MessageBox.show(
                    {
                        title: '服务器异常',
                        msg: operation.getError(),
                        icon: Ext.MessageBox.ERROR,
                        buttons: Ext.Msg.OK
                    });
                }
            }
        },
        listeners: 
        {
            write: function(proxy, operation)
            {
                if (operation.action == 'destroy') 
                {
                    main.child('#form').setActiveRecord(null);
                }
                Ext.MessageBox.show(
          			   {
          					 title: operation.action,
          					 msg: operation.resultSet.message,
                             buttons: Ext.Msg.OK
          			   });
            }
        }
	}
);

//创建一个store用于存储Admin
var adminStore=Ext.create
(	'Ext.data.Store',
	{
		model:'Admin',
		pageSize:50,
        proxy: 
        {
            type: 'ajax',
    		getMethod: function(){ return 'POST'; },
			url:'findadmin',
            reader: 
            {
                type: 'json',
                successProperty: 'success',
                root: 'data',
                messageProperty: 'msg'
            },
            writer: 
            {
                type: 'json',
                writeAllFields: true,
				nameProperty: 'mapping'
            },
            listeners: 
            {
                exception: function(proxy, response, operation)
                {
                    Ext.MessageBox.show(
                    {
                        title: '服务器异常',
                        msg: operation.getError(),
                        icon: Ext.MessageBox.ERROR,
                        buttons: Ext.Msg.OK
                    });
                }
            }
        },
	}
);

//创建一个store用于存储orderlist
var orderlistStore=Ext.create
(	'Ext.data.Store',
	{
		model:'OrderList',
		pageSize:50,
		groupField: 'orderformid',
        proxy: 
        {
            type: 'ajax',
    		getMethod: function(){ return 'POST'; },
			api: 
            {
                read: '',
                create: 'addorderlist',
                update: '',
                destroy: ''
            },
            reader: 
            {
                type: 'json',
                successProperty: 'success',
                root: 'data',
                messageProperty: 'msg'
            },
            writer: 
            {
                type: 'json',
                writeAllFields: true,
				nameProperty: 'mapping'
            },
            listeners: 
            {
                exception: function(proxy, response, operation)
                {
                    Ext.MessageBox.show(
                    {
                        title: '服务器异常',
                        msg: operation.getError(),
                        icon: Ext.MessageBox.ERROR,
                        buttons: Ext.Msg.OK
                    });
                }
            }
        },
		listeners:
		{	
			write:function(store, operation)
			{
				Ext.MessageBox.show(
                    {
                        title: operation.action,
          				msg: operation.resultSet.message,
                        buttons: Ext.Msg.OK
                    });
			}
			
		}
	}
);

//创建一个store用于存储ListForm
var orderformStore=Ext.create
(	'Ext.data.Store',
	{
		model:'OrderForm',
		pageSize:50,
		autoSync:true,	
        proxy: 
        {
            type: 'ajax',
    		getMethod: function(){ return 'POST'; },
            api:
			{
				update:'updateorderform',
			},
			reader: 
            {
                type: 'json',
                successProperty: 'success',
                root: 'data',
                messageProperty: 'msg'
            },
            writer: 
            {
                type: 'json',
                writeAllFields: true,
				nameProperty: 'mapping'
            },
            listeners: 
            {
                exception: function(proxy, response, operation)
                {
                    Ext.MessageBox.show(
                    {
                        title: '服务器异常',
                        msg: operation.getError(),
                        icon: Ext.MessageBox.ERROR,
                        buttons: Ext.Msg.OK
                    });
                }
            }
        },
		listeners:
		{	
			write:function(store, operation)
			{
				Ext.MessageBox.show(
                    {
                        title: operation.action,
          				msg: operation.resultSet.message,
                        buttons: Ext.Msg.OK
                    });
			}
			
		}
	}
);
//创建一个form用添加操作,For 商品种类管理-----------------------------------------------------
Ext.define('CommodityClass.Form', 
{
    extend: 'Ext.form.Panel',
    alias: 'widget.typeform',
    requires: ['Ext.form.field.Text'],
	//初始化函数
    initComponent: function(){
		//添加create事件
        this.addEvents('create');
        Ext.apply(this, 
        {
            activeRecord: null,  // 定义activeRecord属性
            iconCls: 'icon-user',
			cls:'form',
            frame: true,
            title: '操作面板',
            defaultType: 'textfield',
            bodyPadding: 5,
            fieldDefaults: 
            {
                anchor: '40%',
                labelAlign: 'left',
            },
            items:
            [
             	{
                	fieldLabel: '种类编码',
                	name: 'typeid',
                	height:25,
                	readOnly:true,
                	emptyText:'用户不能指定种类编码',
                	disabled:true
            	}, 
           		{
                	fieldLabel: '种类名称',
                	name: 'typename',
                	allowBlank: false,
                	height:25,
                	emptyText:'如要添加种类信息,请输入名称'
            	},
            	{
                	fieldLabel: '种类描述',
                	name: 'typedesc',
                	allowBlank: false,
                	height:25,
                	emptyText:'如要添加种类信息,请输入描述内容'
            	}
            ],
            dockedItems: [{
                xtype: 'toolbar',
                dock: 'bottom',
                ui: 'footer',
                items: ['->', {
                    iconCls: 'icon-save',
                    itemId: 'save',
                    text: '修改',
                    disabled: true,
                    scope: this,
                    handler: this.onSave
                }, {
                    iconCls: 'icon-user-add',
                    text: '创建',
                    scope: this,
                    handler: this.onCreate
                }, {
                    iconCls: 'icon-reset',
                    text: '重置',
                    scope: this,
                    handler: this.onReset
                }]
            }]
        });
        this.callParent(); //调用父类的initComponent方法。
    },

    setActiveRecord: function(record){
        this.activeRecord = record;
        if (record) {
            this.down('#save').enable();
			//Loads an Ext.data.Model into this form 
			//param: record: Ext.data.Model, The record to load
            this.getForm().loadRecord(record); 
        } else {
            this.down('#save').disable();
            this.getForm().reset();
        }
    },
	
    onSave: function(){
        var active = this.activeRecord,
            form = this.getForm();
        if (!active) {
            return;
        }
        if (form.isValid()) {
			//Persists the values in this form into the passed Ext.data.Model
			//object in a beginEdit/endEdit block. If the record is not 
			//specified, it will attempt to update (if it exists) the record 
			//provided to loadRecord.
            form.updateRecord(active);
			this.onReset(); 
        }
    },
    onCreate: function(){
        var form = this.getForm();
        if (form.isValid()) {
            this.fireEvent('create', this, form.getValues());
            form.reset();
        }
    },
    onReset: function(){
        this.setActiveRecord(null);
        this.getForm().reset();
    }
});

//创建一个form用添加操作,For OrderList管理-----------------------------------------------------
Ext.define('OrderList.Form', 
{
    extend: 'Ext.form.Panel',
    alias: 'widget.orderlistform',
    requires: ['Ext.form.field.Text'],
	//初始化函数
    initComponent: function(){
		//添加create事件
        this.addEvents('create');
        Ext.apply(this, 
        {
            activeRecord: null,  // 定义activeRecord属性
            iconCls: 'icon-user',
            frame: true,
            title: '订单详情面板',
            defaultType: 'textfield',
			height:70,
			layout:
			{
				type:'table',
				columns:5
			},
            items:
            [
           		{
					fieldLabel: '订单编号',
					labelAlign:'left',
					labelWidth:60,
					margin:5,
                	name: 'orderformid',
                	allowBlank: false,
            	},
				{
					xtype:'combobox',
					fieldLabel: '商品名称',
					name: 'commodityname',		
					labelAlign:'left',
					labelWidth:60,
					margin:5,
					store:commodityStore, //使用userStore数据提供下拉列表项的数据
					displayField:'commodityname', //列表项的显示文本使用typename的字段值
					queryMode:'remote',	//指定使用store中已有的数据
					queryParam:'commodityname',
					minChars:2,
					allowBlank:false,
					listeners:
					{
						select:function(combo,ecords)
						{
							Ext.getCmp('comboxnum').reset();
							commodityStore.reload
							({
								params:
								{
									commodityname:combo.value,
								}
							});
						}
					}
				},
				{
					id:'comboxnum',
					xtype:'combobox',
					fieldLabel: '商品剩余数量',
					name: 'commodityid',		
					labelAlign:'left',
					labelWidth:80,
					width:200,
					margin:5,
					store:commodityStore, //使用userStore数据提供下拉列表项的数据
					displayField:'commodityleavenum', //列表项的显示文本使用typename的字段值
					valueField:'commodityid',
					queryMode:'local',	//指定使用store中已有的数据
					allowBlank:false,
					listeners:
					{
						select:function(combo,ecords)
						{
							field=Ext.getCmp('comboxprice');
							field.reset();
							commodityStore.reload
							({
								params:
								{
									commodityid:combo.value,
								}
							});
							field.setValue(ecords[0].get('commodityprice'));
						}
					}
				},
				{
					id:'comboxprice',
					xtype:'combobox',
					fieldLabel: '商品价格',
					name: 'commodityprice',		
					labelAlign:'left',
					labelWidth:60,
					width:200,
					margin:5,
					store:commodityStore, //使用userStore数据提供下拉列表项的数据
					displayField:'commodityprice', //列表项的显示文本使用typename的字段值
					valueField:'commodityprice',
					queryMode:'local',	//指定使用store中已有的数据
					allowBlank:false
				},
            	{
                	xtype:'numberfield',
					fieldLabel: '需求数量',
					labelAlign:'left',
					labelWidth:60,
					width:200,
					margin:5,
                	name: 'amount',
                	allowBlank: false,
                	emptyText:'需求数量'
            	}
            ],
            dockedItems: 
			[
				{
					xtype: 'toolbar',
					dock: 'right',
					layout:'hbox',
					items:
					['->', 
						{
							iconCls: 'icon-user-add',
							text: '创建',
							formBind:true,
							disabled:true,
							scope: this,
							handler: this.onCreate
						}, 	
						{
							iconCls: 'icon-save',
							itemId: 'save',
							text: '修改',
							formBind:true,
							disabled: true,
							scope: this,
							handler: this.onSave
						}, 
						{
							iconCls: 'icon-reset',
							text: '重置',
							scope: this,
							handler: this.onReset
						}
					]
				}
			]
        });
        this.callParent(); //调用父类的initComponent方法。
    },

    setActiveRecord: function(record){
        this.activeRecord = record;
        if (record) {
            this.down('#save').enable();
			//Loads an Ext.data.Model into this form 
			//param: record: Ext.data.Model, The record to load
            this.getForm().loadRecord(record); 
        } else {
            this.down('#save').disable();
            this.getForm().reset();
        }
    },
	
    onSave: function(){
        var active = this.activeRecord,
            form = this.getForm();
        if (!active) {
            return;
        }
        if (form.isValid()) {
			//Persists the values in this form into the passed Ext.data.Model
			//object in a beginEdit/endEdit block. If the record is not 
			//specified, it will attempt to update (if it exists) the record 
			//provided to loadRecord.
            form.updateRecord(active);
			this.onReset(); 
        }
    },
    onCreate: function(){
        var form = this.getForm();
        if (form.isValid())
			{
            this.fireEvent('create', this, form.getValues());
            this.items.get(1).reset();
			this.items.get(2).reset();
			this.items.get(3).reset();
			this.items.get(4).reset();
			Ext.getCmp('orderlistgridid').getView().refresh();
        }
    },
    onReset: function(){
        this.setActiveRecord(null);
        this.getForm().reset();
    }
});
//创建一个form用添加操作,For OrderForm-----------------------------------------------------
Ext.define('OrderForm.Form', 
{
    extend: 'Ext.form.Panel',
    alias: 'widget.orderformform',
    requires: ['Ext.form.field.Text'],
	//初始化函数
    initComponent: function(){
        Ext.apply(this, 
        {
            iconCls: 'icon-user',
			activeRecord: null,
			cls:'form',
            frame: true,
            title: '订单面板',
            defaultType: 'textfield',
            layout:
			{
				type:'table',
				columns:4
			},
            items:
            [
				{
					xtype:'combobox',
					fieldLabel: '用户名',
					name: 'userid',
					labelAlign:'left',
					labelWidth:60,
					margin:5,
					store:userStore, //使用userStore数据提供下拉列表项的数据
					displayField:'phone', //列表项的显示文本使用typename的字段值
					valueField:'userid', //列表项的值使用typeid值
					queryMode:'remote',	//指定使用store中已有的数据
					queryParam:'phone',
					minChars:2,
					allowBlank:true
				}, 
            	{
                	xtype:'datefield',
					fieldLabel: '购买时间',
                	name: 'submittime',
					format:'Y-m-d',
                	allowBlank: true,
					labelAlign:'left',
					labelWidth:60,
					margin:5,
            	},
				{
                	xtype:'numberfield',
					fieldLabel: '应收',
                	name: 'totalprice',
					format:'0.000',
                	allowBlank: true,
                	labelAlign:'left',
					labelWidth:60,
					margin:5,
            	},
				{
                	xtype:'numberfield',
					fieldLabel: '结账状况',
                	name: 'ispayoff',
					format:'0.000',
                	allowBlank: true,
                	labelAlign:'left',
					labelWidth:60,
					margin:5,
            	}
				
            ],
            dockedItems: [{
                xtype: 'toolbar',
                dock: 'right',
				layout:'hbox',
                items: 
				[ 
					{
						iconCls: 'icon-user-add',
						text: '创建',
						scope: this,
						handler: this.onSave
					}, 
					{
						iconCls: 'icon-user',
						text: '查询',
						scope: this,
						handler: this.onFind
					}, 
					{
						iconCls: 'icon-reset',
						text: '重置',
						scope: this,
						handler: this.onReset
					}
				]
            }]
        });
        this.callParent(); //调用父类的initComponent方法。
    },
	
	setActiveRecord: function(record){
        this.activeRecord = record;
        if (record) {
			//Loads an Ext.data.Model into this form 
			//param: record: Ext.data.Model, The record to load
            this.getForm().loadRecord(record); 
        } 
    },
	
    onSave: function(){
        var form = this.getForm();
		var value=form.getValues();
        if (form.isValid()) 
		{
			form.submit
			(
				{
					url:'addorderform',
					success:function(form,action)
					{
						Ext.MessageBox.alert('提示信息',action.result.msg);
						orderformStore.reload
						(
							{
								url:'findorderform',
								params:
								{
									submittime:value.submittime,
									userid:value.userid
								}
							}
						);	
					}
				}
			);
        }
    },
	
	onFind:function()
		{
			form = this.getForm();
			value=form.getValues();
				orderformStore.load
				(
					{
						url:'findorderform',
						params:
						{
							submittime:value.submittime,
							userid:value.userid
						}
					}
				);	
		},
		
    onReset: function()
	{
        this.getForm().reset();
    }
});

//创建组件类widget.grid,包含分页显示功能和动态编辑功能 For商品种类信息管理
Ext.define
(	'CommodityClass.Grid',
{
	extend:'Ext.grid.Panel',
	alias: 'widget.typegrid',
	requires: 
	[
    'Ext.grid.plugin.CellEditing',
    'Ext.form.field.Text',
    'Ext.toolbar.TextItem'
	],
	initComponent: function()
	{
		this.editing = Ext.create('Ext.grid.plugin.RowEditing');
		Ext.apply
		(	this, 
			{
				activeRecord: null,
				iconCls: 'icon-grid',
				frame: true,
				title:'显示区域',
				plugins: [this.editing],
				dockedItems: 
				[
	              {
	                weight: 1,
	                xtype: 'toolbar',
	                dock: 'bottom',
	                items: 
	                [
	                	{
	                    	text: '自动更新',
	                    	enableToggle: true,
	                   	 	pressed: false,
	                   	 	tooltip: '当处于按下状态时会自动会自动更新变动内容值database.',
	                    	scope: this,
	                    	toggleHandler: function(btn, pressed){
	                        this.store.autoSync = pressed;
	                   			 }
	               		 }, 
	                	{
	                		xtype:'button',
	    	                iconCls: 'icon-save',
	    	                text: '更新',
	    	                scope: this,
	    	                handler: this.onSync
	    	            },
	    	            {
	    	            	xtype:'button',
	                    	iconCls: 'icon-delete',
	                    	text: '删除',
	                    	tooltip:'请注意,删除相关信息会连带删除相关联的产品信息,情慎重！！！！',
	                    	itemId: 'delete',
	                    	scope: this,
	                    	handler: this.onDeleteClick
	                	}
	                ]
	              },
	          	],
				columns: 
				[
					{
						header: '序号',
						width:40,
						sortable: true,
						xtype:'rownumberer',
					},
					{
						header: '种类编号',
						width:80,
						sortable: true,
						dataIndex: 'typeid',
					}, 
					{
						header: '种类名称',
						sortable: true,
						flex:1,
						dataIndex: 'typename',
					}, 
					{
						header: '种类描述',
						sortable: true,
						flex:1,
						dataIndex: 'typedesc',
					},
				],
			}
		);
		this.callParent();
		this.getSelectionModel().on('selectionchange', this.onSelectChange, this);
	},
	
	
    onSelectChange: function(selModel, selections)
    {
        this.down('#delete').setDisabled(selections.length === 0);
    },

    onSync: function()
    {
        this.store.sync();
    },

    onDeleteClick: function()
    {
        var selection = this.getView().getSelectionModel().getSelection()[0];
        if (selection) 
        {
        	this.store.remove(selection);
        }
    },
});

//创建组件类widget.grid,包含分页显示功能和动态编辑功能 For OrderList
Ext.define
(	'OrderList.Grid',
{
	extend:'Ext.grid.Panel',
	alias: 'widget.orderlistgrid',
	requires: 
	[
    'Ext.grid.plugin.CellEditing',
    'Ext.form.field.Text',
    'Ext.toolbar.TextItem'
	],
	initComponent: function()
	{
		this.editing = Ext.create('Ext.grid.plugin.CellEditing');
		Ext.apply
		(	this, 
			{
				plugins: [this.editing],
				selModel: 
				{
					selType: 'cellmodel'
				},
				activeRecord: null,
				iconCls: 'icon-grid',
				frame: true,
				title:'显示区域',
				dockedItems: 
				[
					{
						weight: 1,
						xtype: 'toolbar',
						dock: 'bottom',
						items: 
						[
							
							{
								text: '批量更新',
								enableToggle: true,
								pressed: false,
								tooltip: '批量更新',
								scope: this,
								toggleHandler: function(btn, pressed){
									this.store.getProxy().batchActions = pressed;
								}
							},
							{
								xtype:'button',
								iconCls: 'icon-save',
								text: '提交',
								scope: this,
								handler: this.onSync
							},
							{
								xtype:'button',
								iconCls: 'icon-delete',
								text: '删除',
								tooltip:'请注意,删除相关信息会连带删除相关联的产品信息,情慎重！！！！',
								itemId: 'delete',
								scope: this,
								handler: this.onDeleteClick
							},
						]
					}
	          	],
				features: 
				[{
					id: 'group',
					ftype: 'groupingsummary',
					hideGroupedHeader: true,
					enableGroupingMenu: false
				}],
				columns: 
				[
					{
						header: '序号',
						width:40,
						sortable: true,
						xtype:'rownumberer'
					}, 
					{
						header: '订单编号',
						sortable: true,
						flex:1,
						dataIndex: 'orderformid',
					},
					{
						header: '商品名称',
						sortable: true,
						flex:1,
						dataIndex: 'commodityname',
					},  
					{
						header: '商品编码',
						sortable: true,
						flex:1,
						dataIndex: 'commodityid',
					}, 
					{
						header: '商品价格',
						xtype:'numbercolumn',
						sortable: true,
						flex:1,
						dataIndex: 'commodityprice',
					},
					{
						xtype:'numbercolumn',
						header: '需求数量',
						sortable: true,
						groupable: false,
						flex:1,
						dataIndex: 'amount',
						field: 
						{
							xtype: 'numberfield'
						},
						summaryType: 'sum',
					},
					{
						xtype:'numbercolumn',
						header: '应付',
						sortable: true,
						groupable: false,
						flex:1,
						renderer: function(value, metaData, record, rowIdx, colIdx, store, view) 
						{
							return (record.get('commodityprice') * record.get('amount'));
						},
						summaryType: function(records)
						{
							var i = 0,
								length = records.length,
								total = 0,
								record;

							for (i=0; i < length; i++) {
								record = records[i];
								total += record.get('commodityprice') * record.get('amount');
							}
							return '￥'+total;
						},
					},
				],
			}
		);
		this.callParent();
		this.getSelectionModel().on('selectionchange', this.onSelectChange, this);
	},
	
	
    onSelectChange: function(selModel, selections)
    {
        this.down('#delete').setDisabled(selections.length === 0);
    },

    onSync: function()
    {
        this.store.sync();
        this.store.removeAll();
		Ext.getCmp('orderformformid').onFind();
		
    },

    onDeleteClick: function()
    {
        var selection = this.getView().getSelectionModel().getSelection()[0];
        if (selection) 
        {
        	this.store.remove(selection);
        }
    },
});
//创建组件类widget.grid, For OrderForm信息管理
Ext.define
(	'OrderForm.Grid',
{
	extend:'Ext.grid.Panel',
	alias: 'widget.orderformgrid',
	requires: 
	[
    'Ext.grid.plugin.CellEditing',
    'Ext.form.field.Text',
    'Ext.toolbar.TextItem'
	],
	initComponent: function()
	{
		this.editing = Ext.create('Ext.grid.plugin.CellEditing');
		Ext.apply
		(	this, 
			{
				plugins: [this.editing],
				iconCls: 'icon-grid',
				activeRecord: null,
				frame: true,
				title:'显示区域',
				columns: 
				[
					{
						header: '序号',
						width:40,
						sortable: true,
						xtype:'rownumberer',
					},
					{
						header: '订单编号',
						width:80,
						sortable: true,
						dataIndex: 'orderformid',
					}, 
					{
						header: '顾客名称',
						sortable: true,
						flex:1,
						dataIndex: 'name',
					}, 
					{
						header: '购买时间',
						xtype:'datecolumn',
						sortable: true,
						format:'Y-m-d',
						flex:1,
						dataIndex: 'submittime',
					},
					{
						header: '总价格',
						xtype:'numbercolumn',
						allowDecimals:true,
						format:'0.00',
						sortable: true,
						flex:1,
						dataIndex: 'totalprice',
					},
					{
						header: '结账状况',
						xtype:'numbercolumn',
						allowDecimals:true,
						format:'0.00',
						sortable: true,
						flex:1,
						dataIndex: 'ispayoff',
						field: 
						{
							xtype: 'numberfield'
						},
					},
				],
			}
		);
		this.callParent();
		this.getSelectionModel().on('selectionchange', this.onSelectChange, this);
	},
	
	
    onSelectChange: function(selModel, selections)
    {
       //waiting 后补  
    },
});

//创建组件类widget.grid,包含分页显示功能和动态编辑功能 For员工管理
Ext.define
(	'Admin.Grid',
{
	extend:'Ext.grid.Panel',
	alias: 'widget.admingrid',
	requires: 
	[
    'Ext.grid.plugin.CellEditing',
    'Ext.form.field.Text',
    'Ext.toolbar.TextItem'
	],
	initComponent: function()
	{
		this.editing = Ext.create('Ext.grid.plugin.RowEditing');
		Ext.apply
		(	this, 
			{
				activeRecord: null,
				iconCls: 'icon-grid',
				frame: true,
				title:'显示区域',
				columns: 
				[
					{
						header: '序号',
						width:40,
						sortable: true,
						xtype:'rownumberer',
					},
					{
						header: '员工编号',
						width:80,
						sortable: true,
						dataIndex: 'adminid',
					}, 
					{
						header: '员工姓名',
						sortable: true,
						flex:1,
						dataIndex: 'username',
					}, 
					{
						header: '密码',
						sortable: true,
						flex:1,
						dataIndex: 'password',
					},
					{
						header: '性别',
						sortable: true,
						flex:1,
						dataIndex: 'sex',
					},
					{
						header: '入职日期',
						xtype:'datecolumn',
						format:'Y-m-d',
						sortable: true,
						flex:1,
						dataIndex: 'hireday',
					},
					{
						header:'操作',
						xtype:'actioncolumn',
						items:
						[
							{
								icon:'http://localhost:8080/supermarketOnlineManager/img/edit.gif',
								tooltip:'编辑',
								handler:editAdmin,
							}
						]
					}
				],
			}
		);
		this.callParent();
	},
});

//创建组件类widget.grid,包含分页显示功能和动态编辑功能 For商品信息管理---------------------------------------------
Ext.define
(	'Comm.Grid',
{
	extend:'Ext.grid.Panel',
	alias: 'widget.commgrid',
	initComponent: function()
	{
		Ext.apply
		(	this, 
			{
				iconCls: 'icon-grid',
				title:'显示区域',
				frame: true,
				margin:0,
				dockedItems:
				[
					{
						xtype:'toolbar',
						items:
						[
							{
								text:'添加商品信息',
								iconCls:'icon-edit',
								handler:addComm,
							}
						]
					},
				],
				columns: 
				[
					{
						header: '序号',
						width:40,
						sortable: true,
						xtype:'rownumberer',
					},
					{
						header: '商品编号',
						sortable: true,
						flex:1,
						dataIndex: 'commodityid',
					},
					{
						header: '种类',
						sortable: true,
						flex:1,
						dataIndex: 'typename',
					}, 
					{
						header: '种类编码',
						sortable: true,
						flex:1,
						dataIndex: 'typeid',
					}, 
					{
						header: '商品名称',
						sortable: true,
						flex:1,
						dataIndex: 'commodityname',
					}, 
					{
						header: '商品描述',
						sortable: true,
						flex:1,
						dataIndex: 'commoditydesc',
					},
					{
						header: '生产厂家',
						sortable: true,
						flex:1,
						dataIndex: 'manufacture',
					},
					{
						header: '商品价格',
						xtype:'numbercolumn',
						format:'0.000',
						sortable: true,
						flex:1,
						dataIndex: 'commodityprice',
					},
					{
						header: '参考价格',
						xtype:'numbercolumn',
						sortable: true,
						flex:1,
						dataIndex: 'fcprice',
					},
					{
						header: '商品数量',
						xtype:'numbercolumn',
						sortable: true,
						format:'000000',
						flex:1,
						dataIndex: 'commodityamount',
					},
					{
						header: '商品剩余数量',
						xtype:'numbercolumn',
						readOnly:true,
						sortable: true,
						format:'000000',
						flex:1,
						dataIndex: 'commodityleavenum',
					},
					{
						header: '添加日期',
						xtype:'datecolumn',
						format:'Y年m月d日',
						sortable: true,
						flex:1,
						dataIndex: 'tagtime',
					},
					{
						header:'操作',
						xtype:'actioncolumn',
						items:
						[
							{
								icon:'http://localhost:8080/supermarketOnlineManager/img/edit.gif',
								tooltip:'编辑',
								handler:editCommotity,
							},
							{
								icon:'http://localhost:8080/supermarketOnlineManager/img/delete.png',
								tooltip:'删除',
								handler:deleteCommodity,
							}
						]
					}
				],
			}
		);
		this.callParent();
	},
	
});
 
//创建组件类widget.grid,包含分页显示功能和动态编辑功能 For用户信息---------------------------------------------
Ext.define
(	'User.Grid',
{
	extend:'Ext.grid.Panel',
	alias: 'widget.usergrid',
	initComponent: function()
	{
		Ext.apply
		(	this, 
			{
				iconCls: 'icon-grid',
				title:'显示区域',
				frame: true,
				margin:0,
				dockedItems:
				[
					{
						xtype:'toolbar',
						items:
						[
							{
								text:'添加新用户',
								iconCls:'icon-edit',
								handler:addUser,
							}
						]
					},
				],
				columns: 
				[
					{
						header: '序号',
						width:40,
						sortable: true,
						xtype:'rownumberer',
					},
					{
						header: '用户编号',
						sortable: true,
						flex:1,
						dataIndex: 'userid',
					},
					{
						header: '用户名',
						sortable: true,
						flex:1,
						dataIndex: 'username',
					}, 
					{
						header: '姓名',
						sortable: true,
						flex:1,
						dataIndex: 'name',
					},
					{
						header: '性别',
						sortable: true,
						flex:1,
						dataIndex: 'sex',
					},
					{
						header: '住址',
						sortable: true,
						flex:1,
						dataIndex: 'address',
					},
					{
						header: '邮寄地址',
						sortable: true,
						flex:1,
						dataIndex: 'postaddress',
					},
					{
						header: '电话',
						sortable: true,
						flex:1,
						dataIndex: 'phone',
					},
					{
						header: 'Email',
						sortable: true,
						flex:1,
						dataIndex: 'email',
					},
					{
						header: '添加日期',
						xtype:'datecolumn',
						format:'Y年m月d日',
						sortable: true,
						flex:1,
						dataIndex: 'tagtime',
					},
					{
						header:'操作',
						xtype:'actioncolumn',
						items:
						[
							{
								icon:'http://localhost:8080/supermarketOnlineManager/img/edit.gif',
								tooltip:'编辑',
								handler:editUser,	
							},
							{
								icon:'http://localhost:8080/supermarketOnlineManager/img/delete.png',
								tooltip:'删除',
								//
							}
						]
					}
				],
			}
		);
		this.callParent();
	},
	
});
//定义增加商品的事件处理函数
	var addCommWin;
	var addComm=function()
	{										
		if(addCommWin)
		{
			var form=addCommWin.items.get(0).getForm();
			form.reset();
			addCommWin.show();
		}
		else
		{
			addCommWin=Ext.create
			(	'Ext.window.Window',
				{
					title:'添加商品信息',
					layout:'fit',
					closable:true,
					shadow:true,
					modal:true,
					items:
					[	
						{
							xtype:'form',
							frame: true,
							width:400,
							height:400,
							method:'POST',
							defaultType: 'textfield',
							bodyPadding: 5,
							fieldDefaults: 
							{
								anchor: '95%',
								labelAlign: 'left',
							},
							items:
							[
								{
									fieldLabel:'商品编号',
									name:'commodityid',
									readOnly:true,
									height:25,
									emptyText:'产品id不可变更或者指定'
								},
								{
									xtype:'combobox',
									fieldLabel: '选择商品种类',
									name: 'typeid',
									height:25,
									store:commodityClassStore2, //使用commodityClassStore数据提供下拉列表项的数据
									displayField:'typename', //列表项的显示文本使用typename的字段值
									valueField:'typeid', //列表项的值使用typeid值
									queryMode:'local',	//指定使用store中已有的数据
									queryParam:'typename',
									allowBlank:false
								}, 
								{
									fieldLabel: '商品名称',
									name: 'commodityname',
									allowBlank: false,
									height:25,
									emptyText:'请输入商品名称'
								},
								{
									
									fieldLabel: '生产厂家',
									name: 'manufacture',
									allowBlank: false,
									height:25,
									emptyText:'请输入生产厂家'
								},
								{
									
									fieldLabel: '商品描述',
									name: 'commoditydesc',
									allowBlank: true,
									height:25,
									emptyText:'请输入商品描述'
								},
								{
									
									xtype:'numberfield',
									fieldLabel: '商品价格',
									name: 'commodityprice',
									allowBlank: false,
									allowDecimals:true,
									height:25,
									emptyText:'请输入商品价格'
								},
								{
									
									xtype:'numberfield',
									fieldLabel: '参考价格',
									name: 'fcprice',
									allowBlank: false,
									allowDecimals:true,
									height:25,
									emptyText:'请输入参考价格'
								},
								{
									
									xtype:'numberfield',
									fieldLabel: '商品数量',
									name: 'commodityamount',
									allowBlank: false,
									height:25,
									emptyText:'请输入商品数量'
								},
								{
									
									xtype:'datefield',
									fieldLabel: '添加日期',
									name: 'tagtime',
									allowBlank: false,
									format:'Y-m-d',
									altFormats:'Ymd|Y/m/d',
									height:25,
									emptyText:'请选择日期',
								}
							],
							bbar: 
							[	
								{
									iconCls: 'icon-save',
									itemId: 'save',
									text: '保存',
									formBind: true, // 只有当整个表单输入校验通过时，该按钮才可用
									disabled: true, // 设置该按钮默认不可用
									handler:function()
									{
										var form=addCommWin.items.get(0).getForm();
										if(form.isValid())
										{
											form.submit
											(
												
												{
													submitEmptyText:false,
													url:'addcomm', 
													success:function(form,action)
													{
														addCommWin.hide();
														Ext.MessageBox.alert("提示信息",action.result.msg);
													}
												}
											);
										}
									}
								},
								{
									iconCls: 'icon-reset',
									text: '重置',
									handler: function()
									{
										var form=addCommWin.items.get(0).getForm();
										form.reset();
									}
								},
								{
									iconCls: 'icon-delete',
									text: '取消',
									handler: function()
									{
										addCommWin.hide();
									}
								}
							],
						}
					],
					listeners:
					{
						beforedestroy:function()
						{
							this.hide();
							return false;
						}
					}
				}
			).show();
		};
	};

//定义增加用户信息的的事件处理函数
	var useraddWin;
	var addUser=function()
	{										
		if(useraddWin)
		{
			var form=useraddWin.items.get(0).getForm();
			form.reset();
			useraddWin.show();
		}
		else
		{
			useraddWin=Ext.create
			(	'Ext.window.Window',
				{
					title:'添加用户信息',
					layout:'fit',
					closable:true,
					shadow:true,
					modal:true,
					items:
					[	
						{
							xtype:'form',
							frame: true,
							width:400,
							height:400,
							method:'POST',
							defaultType: 'textfield',
							bodyPadding: 5,
							fieldDefaults: 
							{
								anchor: '95%',
								labelAlign: 'left',
							},
							items:
							[
								{
									fieldLabel:'用户编号',
									name:'userid',
									readOnly:true,
									height:25,
									emptyText:'用户编码不可变更或者被指定'
								},
								{
									fieldLabel: '用户名',
									name: 'username',
									allowBlank: false,
									minLength:4,
									maxLength:16,
									height:25,
									emptyText:'请输入用户名'
								},
								{
									
									fieldLabel: '密码',
									name: 'password',
									inputType:'password',
									allowBlank: false,
									minLength:6,
									height:25,
									emptyText:'请输入密码'
								},
								{
									
									fieldLabel: '姓名',
									name: 'name',
									allowBlank: false,
									minLength:2,
									maxLength:16,
									height:25,
									emptyText:'请输入姓名'
								},
								{
									xtype:'radiogroup',
									fieldLabel: "性别", // 表单控件的Label
									columns:2,
									vertical:true,
									items:
										[
											{boxLabel:'男',name:'sex',inputValue:'男',checked:true},
											{boxLabel:'女',name:'sex',inputValue:'女'}
										],
								},
								{
									fieldLabel: '住址',
									name: 'address',
									allowBlank: false,
									height:25,
									emptyText:'请输入住址'
								},
								{
									fieldLabel: '邮寄地址',
									name: 'postaddress',
									allowBlank: false,
									height:25,
									emptyText:'请输入邮寄地址'
								},
								{
									fieldLabel: '电话号码',
									name: 'phone',
									allowBlank: false,
									regex:/\d{11}/,
									regexText:'电话号码为：11位',
									height:25,
									emptyText:'请输入电话号码'
								},
								{
									fieldLabel: '电子邮件',
									name: 'email',
									allowBlank: false,
									vtype:'email',
									height:25,
									emptyText:'请输入电子邮件地址',
								},
								{
	
									xtype:'datefield',	
									fieldLabel: '注册时间',
									name: 'tagtime',
									format:'Y-m-d',
									altFormats:'Ymd|Y/m/d',
									allowBlank: false,
									height:25,
									emptyText:'请选择日期',
								}
							],
							bbar: 
							[	
								{
									iconCls: 'icon-save',
									itemId: 'save',
									text: '保存',
									formBind: true, // 只有当整个表单输入校验通过时，该按钮才可用
									disabled: true, // 设置该按钮默认不可用
									handler:function()
									{
										var form=useraddWin.items.get(0).getForm();
										if(form.isValid())
										{
											form.submit
											(
												
												{
													submitEmptyText:false,
													url:'adduser', 
													success:function(form,action)
													{
														useraddWin.hide();
														Ext.MessageBox.alert("提示信息",action.result.msg);
													}
												}
											);
										}
									}
								},
								{
									iconCls: 'icon-reset',
									text: '重置',
									handler: function()
									{
										var form=useraddWin.items.get(0).getForm();
										form.reset();
									}
								},
								{
									iconCls: 'icon-delete',
									text: '取消',
									handler: function()
									{
										useraddWin.hide();
									}
								}
							],
						}
					],
					listeners:
					{
						beforedestroy:function()
						{
							this.hide();
							return false;
						}
					}
				}
			).show();
		};
	};

//创建修改商品信息的事件处理函数
var updatecommWin;
var editCommotity=function(view,rowIndex,colIndex)
{
	var  rec=view.getStore().getAt(rowIndex);
	if(updatecommWin)
	{
		updatecommWin.setTitle("修改数据");
		var formFileds=updatecommWin.items.get(0).items;
		formFileds.get(0).setValue(rec.get('commodityid'));
		formFileds.get(1).setValue(rec.get('typeid'));
		formFileds.get(2).setValue(rec.get('commodityname'));
		formFileds.get(3).setValue(rec.get('manufacture'));
		formFileds.get(4).setValue(rec.get('commoditydesc'));
		formFileds.get(5).setValue(rec.get('commodityprice'));
		formFileds.get(6).setValue(rec.get('fcprice'));
		formFileds.get(7).setValue(rec.get('commodityamount'));
		formFileds.get(8).setValue(rec.get('tagtime'));
		updatecommWin.show();	
	}else
	{
		updatecommWin=Ext.create
			(	'Ext.window.Window',
				{
					title:'修改商品信息',
					layout:'fit',
					closable:true,
					shadow:true,
					modal:true,
					items:
					[	
						{
							xtype:'form',
							frame: true,
							width:400,
							height:400,
							method:'POST',
							defaultType: 'textfield',
							bodyPadding: 5,
							fieldDefaults: 
							{
								anchor: '95%',
								labelAlign: 'left',
							},
							items:
							[
								{
									fieldLabel:'产品编号',
									value:rec.get('commodityid'),
									name:'commodityid',
									readOnly:true,
									height:25,
									emptyText:'产品id不可变更或者指定'
							
								},
								{
									xtype:'combobox',
									fieldLabel: '选择商品种类',
									name: 'typeid',
									height:25,
									store:commodityClassStore2, //使用commodityClassStore数据提供下拉列表项的数据
									displayField:'typename', //列表项的显示文本使用typename的字段值
									valueField:'typeid', //列表项的值使用typeid值
									queryMode:'remote',	//指定使用store中已有的数据
									queryParam:'typename',
									minChars:2,
									queryParam:'name',
									allowBlank:false
								}, 
								
								{
									fieldLabel: '商品名称',
									name: 'commodityname',
									value:rec.get('commodityname'),
									allowBlank: false,
									height:25,
									emptyText:'请输入商品名称'
								},
								{
									value:rec.get('manufacture'),
									fieldLabel: '生产厂家',
									name: 'manufacture',
									allowBlank: false,
									height:25,
									emptyText:'请输入生产厂家'
								},
								{
									value:rec.get('commoditydesc'),
									fieldLabel: '商品描述',
									name: 'commoditydesc',
									allowBlank: true,
									height:25,
									emptyText:'请输入商品描述'
								},
								{
									value:rec.get('commodityprice'),
									xtype:'numberfield',
									fieldLabel: '商品价格',
									name: 'commodityprice',
									allowBlank: false,
									allowDecimals:true,
									height:25,
									emptyText:'请输入商品价格'
								},
								{
									value:rec.get('fcprice'),
									xtype:'numberfield',
									fieldLabel: '参考价格',
									name: 'fcprice',
									allowBlank: false,
									allowDecimals:true,
									height:25,
									emptyText:'请输入参考价格'
								},
								{
									value:rec.get('commodityamount'),
									xtype:'numberfield',
									fieldLabel: '商品数量',
									name: 'commodityamount',
									allowBlank: false,
									height:25,
									emptyText:'请输入商品数量'
								},
								{
									value:rec.get('tagtime'),
									xtype:'datefield',
									fieldLabel: '添加日期',
									name: 'tagtime',
									allowBlank: false,
									format:'Y-m-d',
									altFormats:'Ymd|Y/m/d',
									height:25,
									emptyText:'请选择日期',
								}
							],
							bbar: 
							[	
								{
									iconCls: 'icon-save',
									itemId: 'save',
									text: '保存',
									formBind: true, // 只有当整个表单输入校验通过时，该按钮才可用
									disabled: true, // 设置该按钮默认不可用
									handler:function()
									{
										var form=updatecommWin.items.get(0).getForm();
										if(form.isValid())
										{
											form.submit
											(
												
												{
													url:'editcomm',
													success:function(form,action)
													{
														updatecommWin.hide();
														Ext.MessageBox.alert("提示信息",action.result.msg);
													}
												}
											);
										}
									}
								},
								{
									iconCls: 'icon-reset',
									text: '重置',
									handler: function()
									{
										var form=updatecommWin.items.get(0).getForm();
										form.reset();
									}
								},
								{
									iconCls: 'icon-delete',
									text: '取消',
									handler: function()
									{
										updatecommWin.hide();
									}
								}
							],
						}
					],
					listeners:
					{
						beforedestroy:function()
						{
							this.hide();
							return false;
						}
					}
				}
			)
	}
};

//创建修改用户信息的事件处理函数
var updateuserWin;
var editUser=function(view,rowIndex,colIndex)
{
	var  rec=view.getStore().getAt(rowIndex);
	if(updateuserWin)
	{
		updateuserWin.setTitle("修改用户数据");
		var formFileds=updateuserWin.items.get(0).items;
		formFileds.get(0).setValue(rec.get('userid'));
		formFileds.get(1).setValue(rec.get('username'));
		formFileds.get(2).setValue(rec.get('password'));
		formFileds.get(3).setValue(rec.get('name'));
		formFileds.get(4).setValue(rec.get('sex'));
		formFileds.get(5).setValue(rec.get('address'));
		formFileds.get(6).setValue(rec.get('postaddress'));
		formFileds.get(7).setValue(rec.get('phone'));
		formFileds.get(8).setValue(rec.get('email')),
		formFileds.get(9).setValue(rec.get('tagtime'));
		updateuserWin.show();	
	}else
	{
		updateuserWin=Ext.create
			(	'Ext.window.Window',
				{
					title:'修改用户信息',
					layout:'fit',
					closable:true,
					shadow:true,
					modal:true,
					items:
					[	
						{
							xtype:'form',
							frame: true,
							width:400,
							height:400,
							method:'POST',
							defaultType: 'textfield',
							bodyPadding: 5,
							fieldDefaults: 
							{
								anchor: '95%',
								labelAlign: 'left',
							},
							items:
							[
								{
									fieldLabel:'用户编号',
									name:'userid',
									readOnly:true,
									value:rec.get('userid'),
									height:25,
									emptyText:'用户编码不可变更或者被指定'
								},
								{
									fieldLabel: '用户名',
									name: 'username',
									value:rec.get('username'),
									allowBlank: false,
									minLength:4,
									maxLength:16,
									height:25,
									emptyText:'请输入用户名'
								},
								{
									
									fieldLabel: '密码',
									name: 'password',
									inputType:'password',
									value:rec.get('password'),
									allowBlank: false,
									minLength:6,
									height:25,
									emptyText:'请输入密码'
								},
								{
									
									fieldLabel: '姓名',
									name: 'name',
									value:rec.get('name'),
									allowBlank: false,
									minLength:2,
									maxLength:16,
									height:25,
									emptyText:'请输入姓名'
								},
								{
									xtype:'radiogroup',
									fieldLabel: "性别", // 表单控件的Label
									value:rec.get('sex'),
									columns:2,
									vertical:true,
									items:
										[
											{boxLabel:'男',name:'sex',inputValue:'男',checked:true},
											{boxLabel:'女',name:'sex',inputValue:'女'}
										],
								},
								{
									fieldLabel: '住址',
									name: 'address',
									value:rec.get('address'),
									allowBlank: false,
									height:25,
									emptyText:'请输入住址'
								},
								{
									fieldLabel: '邮寄地址',
									name: 'postaddress',
									value:rec.get('postaddress'),
									allowBlank: false,
									height:25,
									emptyText:'请输入邮寄地址'
								},
								{
									fieldLabel: '电话号码',
									name: 'phone',
									value:rec.get('phone'),
									allowBlank: false,
									regex:/\d{11}/,
									regexText:'电话号码为：11位',
									height:25,
									emptyText:'请输入电话号码'
								},
								{
									fieldLabel: '电子邮件',
									name: 'email',
									value:rec.get('email'),
									allowBlank: false,
									vtype:'email',
									height:25,
									emptyText:'请输入电子邮件地址',
								},
								{
	
									xtype:'datefield',	
									fieldLabel: '注册时间',
									value:rec.get('tagtime'),
									format:'Y-m-d',
									altFormats:'Ymd|Y/m/d',
									name: 'tagtime',
									allowBlank: false,
									height:25,
									emptyText:'请选择日期',
								}
							],
							bbar: 
							[	
								{
									iconCls: 'icon-save',
									itemId: 'save',
									text: '保存',
									formBind: true, // 只有当整个表单输入校验通过时，该按钮才可用
									disabled: true, // 设置该按钮默认不可用
									handler:function()
									{
										var form=updateuserWin.items.get(0).getForm();
										if(form.isValid())
										{
											form.submit
											(
												
												{
													url:'updateuser', 
													success:function(form,action)
													{
														updateuserWin.hide();
														Ext.MessageBox.alert("提示信息",action.result.msg);
													}
												}
											);
										}
									}
								},
								{
									iconCls: 'icon-reset',
									text: '重置',
									handler: function()
									{
										var form=updateuserWin.items.get(0).getForm();
										form.reset();
									}
								},
								{
									iconCls: 'icon-delete',
									text: '取消',
									handler: function()
									{
										updateuserWin.hide();
									}
								}
							],
						}
					],
					listeners:
					{
						beforedestroy:function()
						{
							this.hide();
							return false;
						}
					}
				}
			)
	}
};

//创建修改员工信息的事件处理函数
var updateadminWin;
var editAdmin=function(view,rowIndex,colIndex)
{
	var  rec=view.getStore().getAt(rowIndex);
	if(updateadminWin)
	{
		updateadminWin.setTitle("修改员工数据");
		var formFileds=updateadminWin.items.get(0).items;
		formFileds.get(0).setValue(rec.get('adminid'));
		formFileds.get(1).setValue(rec.get('username'));
		formFileds.get(2).setValue(rec.get('password'));
		formFileds.get(3).setValue(rec.get('sex'));
		formFileds.get(4).setValue(rec.get('hireday'));
		updateadminWin.show();	
	}else
	{
		updateadminWin=Ext.create
			(	'Ext.window.Window',
				{
					title:'修改员工信息',
					layout:'fit',
					closable:true,
					shadow:true,
					modal:true,
					items:
					[	
						{
							xtype:'form',
							frame: true,
							width:400,
							height:400,
							method:'POST',
							defaultType: 'textfield',
							bodyPadding: 5,
							fieldDefaults: 
							{
								anchor: '95%',
								labelAlign: 'left',
							},
							items:
							[
								{
									fieldLabel: '员工编码',
									name: 'adminid',
									value:rec.get('adminid'),
									height:25,
									readOnly:true,
									emptyText:'不能指定员工编码'
								}, 
								{
									fieldLabel: '员工名称',
									name: 'username',
									value:rec.get('username'),
									allowBlank: false,
									height:25,
									emptyText:'如要添加员工，请输入姓名'
								},
								{
									fieldLabel: '密码',
									name: 'password',
									value:rec.get('password'),
									allowBlank: false,
									height:25,
									emptyText:'如要添加员工,请输入密码'
								},
								{
									xtype:'radiogroup',
									fieldLabel: "性别", // 表单控件的Label
									columns:2,
									value:rec.get('sex'),
									height:25,
									vertical:true,
									items:
										[
											{boxLabel:'男',name:'sex',inputValue:'男',checked:true},
											{boxLabel:'女',name:'sex',inputValue:'女'}
										],
								},
								{
									xtype:'datefield',
									fieldLabel: '入职日期',
									name: 'hireday',
									value:rec.get('hireday'),
									allowBlank: false,
									format:'Y-m-d',
									height:25,
									emptyText:'添加日期'
								},
							],
							bbar: 
							[	
								{
									iconCls: 'icon-save',
									itemId: 'save',
									text: '保存',
									formBind: true, // 只有当整个表单输入校验通过时，该按钮才可用
									disabled: true, // 设置该按钮默认不可用
									handler:function()
									{
										var form=updateadminWin.items.get(0).getForm();
										if(form.isValid())
										{
											form.submit
											(
												
												{
													url:'updateadmin', 
													success:function(form,action)
													{
														updateadminWin.hide();
														Ext.MessageBox.alert("提示信息",action.result.msg);
													}
												}
											);
										}
									}
								},
								{
									iconCls: 'icon-reset',
									text: '重置',
									handler: function()
									{
										var form=updateadminWin.items.get(0).getForm();
										form.reset();
									}
								},
								{
									iconCls: 'icon-delete',
									text: '删除',
									handler:function()
									{
										var form=updateadminWin.items.get(0).getForm();
										if(form.isValid())
										{
											form.submit
											(
												
												{
													url:'deleteadmin', 
													success:function(form,action)
													{
														updateadminWin.hide();
														Ext.MessageBox.alert("提示信息",action.result.msg);
													}
												}
											);
										}
									}
								}
							],
						}
					],
					listeners:
					{
						beforedestroy:function()
						{
							this.hide();
							return false;
						}
					}
				}
			)
	}
}

//创建一个删除商品的事件处理函数
var deleteCommodity=function(view,rowIndex,colIndex)
{
	if(Ext.MessageBox.confirm("确认删除","您是否真的需要删除该记录"))
	{
		var  rec=view.getStore().getAt(rowIndex);
		Ext.Ajax.request
		(	
			{
				url:'deletecomm',
				method:'POST',
				params:
				{
					commodityid:rec.get('commodityid')
				},
				success:function(response)
				{
					Ext.MessageBox.alert("提示：",Ext.JSON.decode(response.responseText).msg);
				}
			}
		);
	}
}

// 创建一个TreeStore,TreeStore负责为Tree提供数据
var store = Ext.create('Ext.data.TreeStore',    
{
	root: 
	{
		expanded: true,
		children: 
		[
			{ 	
				text: "商品种类管理", 
				expanded: true, 
				children: 
				[
					{
						id:'typeByName',
						text:'按种类名称查询',
						leaf:true,
						checked:true,
					},
					{
						id:'type',
						text:'查询所有种类信息',
						leaf:true,
					}
				]
			},
			{ 
				text: "商品管理", 
				expanded: true, 
				children:
				[
					{
						id:'CommDeal',
						text: '商品查询以及处理', 
						leaf: true 
					},
				]
			},
			{ 
				text: '订单管理', 
				expanded: true, 
				children:
				[
					{	id:'orderDeal',
						text: '订单查询', 
						leaf: true 
					},
				]
			},
			{ 
				text: "用户管理", 
				expanded: true, 
				children:
				[
					{
						id:'userDeal',
						text: '用户查询以及处理',
						leaf: true 
					},
					{
						id:'userOwn',
						text: '用户账单查询',
						leaf: true 
					},
				]
			},
			{ 
				text: "员工管理", 
				expanded: true, 
				children:
				[
					{
						id:'adminDeal',
						text: '员工查询以及处理', 
						leaf: true 
					},
				]
			},
		]
	}
});
Ext.onReady(function()
{
Ext.create('Ext.container.Viewport', 
{
	layout: 'border',
	items: 
	[
	// 上面区域的内容
		{
			region: 'north',
			html: '<div><img src="img/head.png"/></div>',
			height: 50,
			border: 10,
			margins: '0 0 5 0'
		},
	// 左边区域的内容
		{
			region: 'west',
			xtype: 'treepanel', // 表明这是Ext.tree.Panel
			title: '功能导航区',
			frame:true,
			width: 200,
			height:300,
			store: store,
			rootVisible: false,
			listeners:
			{
			// 为itemclick事件添加监听器
			itemclick : function(view , record, item)
			{
				if(record.data.leaf && record.data.id=='type')
				{
					// 获取页面中my_center组件,该组件是Ext.tab.Panel组件
					var tabPanel = Ext.getCmp('my_center');
					commodityClassStore.reload();
					// 如果页面上没有该itemid对应的组件
					if(!tabPanel.getComponent(record.data.id))
					{
						// 向Ext.tab.Panel组件中插入一个新的Tab页面
						var tab = tabPanel.add
						(
							{
								// 设置新Tab页面的属性
								id:record.data.id,
								title: record.data.text,
								closable:true,
								items:
								[
									{
										itemId:'typegrid',
										xtype:'typegrid',
										store:commodityClassStore,
										height: 500,
										bbar:
										{
											xtype:'pagingtoolbar',
											displayInfo:true,
											store:commodityClassStore
										},
									},	
								]
							}
						);
					}
					// 激活正在查看的功能tabpanel
					tabPanel.setActiveTab(record.data.id);
				};
				
				if(record.data.leaf && record.data.id=='orderDeal')
				{
					// 获取页面中my_center组件,该组件是Ext.tab.Panel组件
					var tabPanel = Ext.getCmp('my_center');
					// 如果页面上没有该itemid对应的组件
					if(tabPanel.getComponent(record.data.id))
					{	
						tabPanel.setActiveTab(record.data.id);
					}
				};
				//如果点击的item是typeByName时
				if(record.data.leaf && record.data.id=='typeByName')
				{
					// 获取页面中my_center组件,该组件是Ext.tab.Panel组件
					var tabPanel = Ext.getCmp('my_center');
					// 如果页面上没有该itemid对应的组件
					if(!tabPanel.getComponent(record.data.id))
					{
						// 向Ext.tab.Panel组件中插入一个新的Tab页面
						var tab = tabPanel.add
						(
							{
								// 设置新Tab页面的属性
								itemId:record.data.id,
								title: record.data.text,
								closable:true,
								items:
								[
									{
										xtype:'form',
										layout: 'hbox',
										items:
										[
											{ 
												xtype:'textfield',
												id:'typename',
												fieldLabel: "种类名称",
												name: 'name',
												emptyText:'请输入需要查询的种类名称',
												allowBlank:false,
												labelWidth:60,
												width:400,
												height:25,
												margins:'5 10 0 10'	
											},
											{
												xtype:'button',
												text:'重置',
												iconCls: 'icon-reset',
												height:25,
												margins:'5 10 0 10',
												handler:function()
													{
													this.up('form').getForm().reset();
													}
												},
											{
												xtype:'button',
												text:'查找',
												iconCls:'icon-user-add',
												height:25,
												margins:'5 10 5 10',
												formBind: true,
												disabled: true, 
												handler:function()
												{
													var form=this.up('form').getForm();
													var value=form.getValues();
													if(form.isValid())
													{
														commodityClassStore2.load
														(
															{
																params:
																{
																	name:value.name,
																}
															}
														)		
													}
												}
											}	
										],
									},
									{
										id:'gridForTypeByName',
										xtype:'typegrid',
										height: 270,
										store:commodityClassStore2,
										bbar:
										{
											xtype:'pagingtoolbar',
											displayInfo:true,
											store:commodityClassStore2
										},
										listeners: 
										{
											selectionchange: function(selModel, selected) 
											{
												Ext.getCmp('typeformid').setActiveRecord(selected[0] || null);
											}
										}
									},
									{
							            id:'typeformid',
										xtype: 'typeform',
							            height: 160,
							            margins: '0 0 10 0',
							            listeners: {
							                create: function(form3, data){
							                    commodityClassStore2.insert(0,data);
							                }
							            }
							        }
								]
							}
						);
					}
					// 激活正在查看的功能tabpanel
					tabPanel.setActiveTab(record.data.id);
				};
				
				//如果点击的item是adminDeal时
				if(record.data.leaf && record.data.id=='adminDeal')
				{
					// 获取页面中my_center组件,该组件是Ext.tab.Panel组件
					adminStore.load();
					var tabPanel = Ext.getCmp('my_center');
					// 如果页面上没有该itemid对应的组件
					if(!tabPanel.getComponent(record.data.id))
					{
						// 向Ext.tab.Panel组件中插入一个新的Tab页面
						var tab = tabPanel.add
						(
							{
								// 设置新Tab页面的属性
								itemId:record.data.id,
								title: record.data.text,
								closable:true,
								items:
								[
									{
										xtype:'form',
										layout: 'hbox',
										items:
										[
											{ 
												xtype:'textfield',
												itemId:'admin',
												fieldLabel: "员工姓名",
												name: 'name',
												emptyText:'请输入需要查询的员工姓名',
												allowBlank:false,
												labelWidth:60,
												width:400,
												height:25,
												margins:'5 10 0 10'	
											},
											{
												xtype:'button',
												text:'重置',
												iconCls: 'icon-reset',
												height:25,
												margins:'5 10 0 10',
												handler:function()
													{
													this.up('form').getForm().reset();
													}
												},
											{
												xtype:'button',
												text:'查找',
												iconCls:'icon-user-add',
												height:25,
												margins:'5 10 5 10',
												formBind: true,
												disabled: true, 
												handler:function()
												{
													var form=this.up('form').getForm();
													var value=form.getValues();
													if(form.isValid())
													{
														adminStore.reload
														(
															{
																url:'findadminByName',
																params:
																{
																	username:value.name,
																}
															}
														)		
													}
												}
											}	
										],
									},
									{
										xtype:'admingrid',
										height: 400,
										store:adminStore,
										bbar:
										{
											xtype:'pagingtoolbar',
											displayInfo:true,
											store:adminStore
										},	
									},
								]
							}
						);
					}
					// 激活正在查看的功能tabpanel
					tabPanel.setActiveTab(record.data.id);
				};
				
				//选择商品处理按钮时显示如下组件
				if(record.data.leaf && record.data.id=='CommDeal')
				{
					// 获取页面中my_center组件,该组件是Ext.tab.Panel组件
					var tabPanel = Ext.getCmp('my_center');
					// 如果页面上没有该itemid对应的组件
					if(!tabPanel.getComponent(record.data.id))
					{
						// 向Ext.tab.Panel组件中插入一个新的Tab页面
						var tab = tabPanel.add
						(
							{
								// 设置新Tab页面的属性
								itemId:record.data.id,
								title: record.data.text,
								closable:true,
								items:
								[
									{
										title:'查询面板',
										xtype:'form',
										iconCls:'icon-edit ',
										layout: 'hbox',
										items:
										[
											{ 
												xtype:'textfield',
												itemId:'commodityname',
												fieldLabel: "商品名称",
												name: 'commodityname',
												allowBlank:true,
												labelWidth:60,
												height:25,
												margins:'5 10 0 10'	
											},
											{ 
												xtype:'combobox',
												fieldLabel: '选择商品种类',
												name: 'typeid',
												height:25,
												store:commodityClassStore2, //使用commodityClassStore2数据提供下拉列表项的数据
												displayField:'typename', //列表项的显示文本使用typename的字段值
												valueField:'typeid', //列表项的值使用typeid值
												queryMode:'remote', //指定使用store中已有的数据
												queryParam:'name',
												minChars:2,
												labelWidth:80,
												allowBlank:'true',
												margins:'5 10 0 10'	
											},
											{ 
												xtype:'textfield',
												fieldLabel: "生产厂家",
												name: 'manufacture',
												allowBlank:true,
												labelWidth:60,
												height:25,
												margins:'5 10 0 10'	
											},
											{
												xtype:'button',
												iconCls:'icon-reset',
												text:'重置',
												height:25,
												margins:'5 10 0 10',
												handler:function()
												{
													this.up('form').getForm().reset();
												}
											},
											{
												xtype:'button',
												text:'查找',
												iconCls:'icon-user ',
												height:25,
												margins:'5 10 5 10',
												formBind: true,
												disabled: true, 
												handler:function()
												{
													var form=this.up('form').getForm();
													var values=form.getValues();
													if(form.isValid())
													{
														commodityStore.load
														(
															{
																params:
																{
																	commodityname:values.commodityname,
																	typeid:values.typeid,
																	manufacture:values.manufacture
																}
															}
														)
													};
												},
											}	
										],
									},
									{
										xtype:'commgrid',
										store:commodityStore,
										height: 450,
										bbar:
										{
											xtype:'pagingtoolbar',
											displayInfo:true,
											store:commodityStore
										},
									},
								]
							}
						);
					}
					// 激活正在查看的功能tabpanel
					tabPanel.setActiveTab(record.data.id);
				};
				
				//选择用户处理按钮时显示如下组件
				if(record.data.leaf && record.data.id=='userDeal')
				{
					// 获取页面中my_center组件,该组件是Ext.tab.Panel组件
					var tabPanel = Ext.getCmp('my_center');
					// 如果页面上没有该itemid对应的组件
					if(!tabPanel.getComponent(record.data.id))
					{
						// 向Ext.tab.Panel组件中插入一个新的Tab页面
						var tab = tabPanel.add
						(
							{
								// 设置新Tab页面的属性
								itemId:record.data.id,
								title: record.data.text,
								closable:true,
								items:
								[
									{
										title:'查询面板',
										xtype:'form',
										iconCls:'icon-edit ',
										layout: 'hbox',
										items:
										[
											{ 
												xtype:'textfield',
												itemId:'name',
												fieldLabel: "姓名",
												name: 'name',
												allowBlank:true,
												labelWidth:60,
												height:25,
												margins:'5 10 0 10'	
											},
											{ 
												xtype:'textfield',
												fieldLabel: '电话号码',
												name: 'phone',
												height:25,
												labelWidth:80,
												allowBlank:true,
												margins:'5 10 0 10'	
											},
											{
												xtype:'button',
												iconCls:'icon-reset',
												text:'重置',
												height:25,
												margins:'5 10 0 10',
												handler:function()
												{
													this.up('form').getForm().reset();
												}
											},
											{
												xtype:'button',
												text:'查找',
												iconCls:'icon-user ',
												height:25,
												margins:'5 10 5 10',
												formBind: true,
												disabled: true, 
												handler:function()
												{
													var form=this.up('form').getForm();
													var values=form.getValues();
													if(form.isValid())
													{
														userStore.load
														(
															{
																url:'finduser',
																params:
																{
																	name:values.name,
																	phone:values.phone,
																}
															}
														)
													};
												},
											}	
										],
									},
									{
										xtype:'usergrid',
										store:userStore,
										height: 450,
										bbar:
										{
											xtype:'pagingtoolbar',
											displayInfo:true,
											store:userStore
										},
									},
								]
							}
						);
					}
					// 激活正在查看的功能tabpanel
					tabPanel.setActiveTab(record.data.id);
				};
			}
		},
	},
	// 中间面板的内容：使用一个Ext.tab.Panel
		{
			region: 'center',
			id:'my_center',
			xtype: 'tabpanel',
			activeTab: 0,
			items:
			[
				{
					// 设置新Tab页面的属性
					itemId:'orderDeal',
					title: '订单处理',
					items:
					[
						{
							id:'orderformformid',
							xtype: 'orderformform',
						},
						{
							xtype:'orderformgrid',
							height:150,
							store:orderformStore,
							bbar:
							{
								xtype:'pagingtoolbar',
								displayInfo:true,
								store:orderformStore
							},
							listeners: 
							{
								selectionchange: function(selModel, selected) 
								{
									Ext.getCmp('orderlistformid').setActiveRecord(selected[0] || null);
									Ext.getCmp('orderformformid').setActiveRecord(selected[0] || null);
								}
							}
						},
						{
							id:'orderlistformid',
							xtype: 'orderlistform',
							listeners: {
								create: function(form, data){
									orderlistStore.insert(orderlistStore.getCount(),data);
								}
							}
						},
						{
							id:'orderlistgridid',
							xtype:'orderlistgrid',
							height:270,
							store:orderlistStore,
							bbar:
							{
								xtype:'pagingtoolbar',
								displayInfo:true,
								store:orderlistStore
							},
							listeners: 
							{
								selectionchange: function(selModel, selected) 
								{
									Ext.getCmp('orderlistformid').setActiveRecord(selected[0] || null);
								}
							}
						},
					]
				}
			]
		}
	]
});
});