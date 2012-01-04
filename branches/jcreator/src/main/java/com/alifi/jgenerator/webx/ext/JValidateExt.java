/**
 * Project: jgenerator
 * 
 * File Created at 2011-12-21
 * $Id$
 * 
 * Copyright 2008 Alibaba.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Alibaba Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Alibaba.com.
 */
package com.alifi.jgenerator.webx.ext;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.form.FormService;
import com.alibaba.citrus.service.form.Validator;
import com.alibaba.citrus.service.form.configuration.FieldConfig;
import com.alibaba.citrus.service.form.configuration.GroupConfig;
import com.alibaba.citrus.service.form.impl.validation.DateValidator;
import com.alibaba.citrus.service.form.support.FormTool;
import com.alibaba.citrus.service.form.support.FormTool.GroupHelper;
import com.alibaba.citrus.service.form.support.FormTool.GroupInstanceHelper;
import com.alibaba.common.logging.LoggerFactory;
import com.alifi.jgenerator.common.utils.DateUtils;

/**
 * @author bangis.wangdf
 */
public class JValidateExt {
	private static Log logger = LoggerFactory.getLogger(JValidateExt.class);

	private static JValidateExt instance;

	public static JValidateExt getInstance() {
		if (instance == null) {
			instance = new JValidateExt();
		}
		return instance;
	}

	private JValidateExt() {
	}

	@Autowired
	private FormService formService;
	private GroupInstanceHelper[] group;
	private GroupConfig groupConfig;
	/**
	 * 逗号
	 */
	private final String STR_COMMA = ",";
	/**
	 * 大括号前
	 */
	private final String STR_BRACE_PR = "{";
	/**
	 * 大括号后
	 */
	private final String STR_BRACE_PF = "}";

	/**
	 * @param formService
	 */
	public JValidateExt(FormService formService) {
		this.formService = formService;
	}

	public String validate(FormService formService, String[] uris,
			FormTool formTool) {
		this.formService = formService;
		int count = Integer.valueOf(uris[1]) + 1;
		GroupHelper groupHelper = formTool.get(uris[0]);
		GroupInstanceHelper[] gs = new GroupInstanceHelper[count];
		if (count == 1) {
			gs[0]=groupHelper.getDefaultInstance();
		} else {
			for (int i = 0; i < count; i++) {
				gs[i] = groupHelper.getInstance("" + i);
			}
		}
		return validate(uris[0], gs);
	}

	public String validate(String groupName, GroupInstanceHelper... group) {
		if (group == null || this.formService == null) {
			return null;
		}
		this.group = group;
		this.groupConfig = formService.getFormConfig()
				.getGroupConfig(groupName);
		return this.STR_BRACE_PR + createValidate() + this.STR_BRACE_PF;
	}

	/**
	 * 无法获取form中配置的message 这里自定义拼装
	 * 
	 * @return
	 */
	private String createValidate() {
		StringBuffer result = new StringBuffer();

		StringBuffer rules = new StringBuffer("\"rules\":");
		StringBuffer messages = new StringBuffer("\"messages\":");
		rules.append(this.STR_BRACE_PR);
		messages.append(this.STR_BRACE_PR);
		List<FieldConfig> fieldList = groupConfig.getFieldConfigList();
		// 遍历所有字段
		for (int i = 0; i < fieldList.size(); i++) {
			if (i > 0) {
				rules.append(this.STR_COMMA);
				messages.append(this.STR_COMMA);
			}
			FieldConfig fieldCfg = fieldList.get(i);
			List<String> groupKeyList = new ArrayList<String>();
			// 得到字段对应转换后的 key --group为组的时候解决
			for (GroupInstanceHelper g : this.group) {
				groupKeyList.add(g.get(fieldCfg.getName()).getKey());
			}
			// 多个group时
			for (int ii = 0; ii < groupKeyList.size(); ii++) {// String groupKey
				if (ii > 0) {
					rules.append(this.STR_COMMA);
					messages.append(this.STR_COMMA);
				}
				String groupKey = groupKeyList.get(ii);
				// 字段验证配置信息
				List<Validator> valList = fieldCfg.getValidators();
				if (valList != null) {
					// 字段验证开始+消息
					String fieldValPix = JSConfig.fieldValPix.format(groupKey)
							+ this.STR_BRACE_PR;
					StringBuffer valConfig = new StringBuffer(fieldValPix);
					StringBuffer msgConfig = new StringBuffer(fieldValPix);
					for (int j = 0; j < valList.size(); j++) {
						Validator val = valList.get(j);
						if (j > 0) {
							valConfig.append(this.STR_COMMA);
							msgConfig.append(this.STR_COMMA);
						}
						switch (ValidateEmu.toValidateEmu(val.getClass()
								.getSimpleName())) {
						case RequiredValidator:
							doRequiredValidator(valConfig, msgConfig, fieldCfg
									.getDisplayName());
							break;
						case DateValidator:
							doDateValidator(val, valConfig, msgConfig, fieldCfg
									.getDisplayName());
							break;
						case MailAddressValidator:
							doMailAddressValidator(valConfig, msgConfig,
									fieldCfg.getDisplayName());
							break;
						case MultiValuesCountValidator:
							// break;
						case NoopValidator:
							// break;
						case NumberCompareValidator:
							// break;
						case NumberValidator:
							// break;
						case RegexpValidator:
							// break;
						case StringByteLengthValidator:
							// break;
						case StringCompareValidator:
							// break;
						case StringLengthValidator:
							// break;
						case UploadedFileValidator:
							// break;
						case NOVALUE:
							logger.warn("不支持的验证方式:"
									+ val.getClass().getSimpleName());
							unDefinedVal(valConfig, msgConfig);
							continue;
						}
					}
					valConfig.append(this.STR_BRACE_PF);
					msgConfig.append(this.STR_BRACE_PF);
					rules.append(valConfig);
					messages.append(msgConfig);
				}
			}
		}
		rules.append(this.STR_BRACE_PF);
		messages.append(this.STR_BRACE_PF);
		return result.append(rules).append(this.STR_COMMA).append(messages)
				.toString();
	}

	private void unDefinedVal(StringBuffer valConfig, StringBuffer msgConfig) {
		valConfig.append("\"UN_DEFINED\":\"\"");
		msgConfig.append("\"UN_DEFINED\":\"\"");
	}

	/**
	 * @param valConfig
	 * @param msgConfig
	 * @param displayName
	 */
	private void doMailAddressValidator(StringBuffer valConfig,
			StringBuffer msgConfig, String displayName) {
		valConfig.append(JSConfig.val.EMAIL);
		msgConfig.append(JSConfig.msg.EMAIL_FORMAT.format(displayName));
	}

	/**
	 * 必填验证
	 * 
	 * @param val
	 * @param valConfig
	 * @param msgConfig
	 * @param displayName
	 */
	private void doRequiredValidator(StringBuffer valConfig,
			StringBuffer msgConfig, String displayName) {
		valConfig.append(JSConfig.val.REQUIRED);
		msgConfig.append(JSConfig.msg.REQUIRED_FORMAT.format(displayName));
	}

	/**
	 * 日期验证
	 * 
	 * @param val
	 * @param valConfig
	 * @param msgConfig
	 */
	private void doDateValidator(Validator val, StringBuffer valConfig,
			StringBuffer msgConfig, String displayName) {
		DateValidator dvl = (DateValidator) val;
		// 格式验证
		valConfig.append(JSConfig.val.DATE_VALIDATOR_FORMAT.format(dvl
				.getFormat()));
		msgConfig.append(JSConfig.msg.DATE_VALIDATOR_FORMAT.format(displayName,
				dvl.getFormat()));
		// 是否是日期验证
		valConfig.append(JSConfig.val.DATE_VALIDATOR_IS_DATE);
		msgConfig.append(JSConfig.msg.DATE_VALIDATOR_IS_DATE
				.format(displayName));
		// 最小日期
		if (dvl.getMinDate() != null
				&& DateUtils.isDate(dvl.getMaxDate(), dvl.getFormat())) {
			valConfig.append(JSConfig.val.DATE_VALIDATOR_MIN.format(dvl
					.getMinDate()));
		}
		// 最大日期
		if (dvl.getMaxDate() != null
				&& DateUtils.isDate(dvl.getMaxDate(), dvl.getFormat())) {
			valConfig.append(JSConfig.val.DATE_VALIDATOR_MAX.format(dvl
					.getMaxDate()));
		}
	}
}
