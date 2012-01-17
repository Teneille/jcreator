/**
 * Project: jgenerator
 * 
 * File Created at 2011-12-22
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
package com.alifi.web.ext;

/**
 * @author bangis.wangdf
 */
public enum ValidateEmu {
    DateValidator,
    MailAddressValidator,
    MultiValuesCountValidator,
    NoopValidator,
    NumberCompareValidator,
    NumberValidator,
    RegexpValidator,
    RequiredValidator,
    StringByteLengthValidator,
    StringCompareValidator,
    StringLengthValidator,
    UploadedFileValidator,
    NOVALUE;

    public static ValidateEmu toValidateEmu(String str)

    {

        try {

            return valueOf(str);

        }

        catch (Exception ex) {

            return NOVALUE;

        }

    }
}
