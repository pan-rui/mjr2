/**
 * @license Copyright (c) 2003-2014, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	 config.language = "zh-cn";
	 config.image_previewText=' '; //预览区域显示内容
	 config.skin = 'moono';//默认皮肤
	 //config.skin = 'v2';
	 //config.skin = 'office2003';
	 
	 config.toolbar = 'Full';
	 
	 config.font_names='宋体/宋体;黑体/黑体;仿宋/仿宋_GB2312;楷体/楷体_GB2312;隶书/隶书;幼圆/幼圆;微软雅黑/微软雅黑;'+ config.font_names;
	 
	 config.toolbar_Full =
	 [
	     ['Source','-','Save','NewPage','Preview','-','Templates'],
	     ['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print', 'SpellChecker', 'Scayt'],
	     ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
	     ['Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField'],
	     '/',
	     ['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
	     ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],
	     ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
	     ['Link','Unlink','Anchor'],
	     ['Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],
	     '/',
	     ['Styles','Format','Font','FontSize'],
	     ['TextColor','BGColor'],
	     ['Maximize', 'ShowBlocks','-','About']
	 ];

	 config.toolbar_Basic =
	 [
	     ['Bold', 'Italic', '-', 'NumberedList', 'BulletedList', '-', 'Link', 'Unlink','-','About']
	 ];
	 
};
