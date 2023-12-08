/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 *  graphic logo is a trademark of OpenMRS Inc.
 */

package org.openmrs.module.files;

import org.openmrs.BaseOpenmrsMetadata;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "files.FileCategory")
@Table(name = "files_file_category")
public class FileCategory extends BaseOpenmrsMetadata {
  private static final long serialVersionUID = -2210932253603034378L;

  @Id
  @GeneratedValue
  @Column(name = "file_category_id")
  private Integer fileCategoryId;

  @Override
  public Integer getId() {
    return fileCategoryId;
  }

  @Override
  public void setId(Integer id) {
    this.fileCategoryId = id;
  }
}
