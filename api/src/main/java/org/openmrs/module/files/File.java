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

import org.openmrs.BaseOpenmrsData;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "files.File")
@Table(name = "files_file")
public class File extends BaseOpenmrsData {
  public static final String VOIDED_PROP = "voided";
  public static final String FILENAME_PROP = "filename";
  public static final String MIME_TYPE_PROP = "mimeType";
  public static final String FILE_CATEGORY_PROP = "fileCategory";
  private static final long serialVersionUID = -355705706076779900L;
  @Id
  @GeneratedValue
  @Column(name = "file_id")
  private Integer fileId;

  @Column(name = "filename", nullable = false)
  private String filename;

  @Column(name = "mime_type")
  private String mimeType;

  @ManyToOne(optional = false)
  @JoinColumn(name = "file_category_id", nullable = false)
  private FileCategory fileCategory;

  @Lob
  @Column(name = "file_data")
  private byte[] fileData;

  @Override
  public Integer getId() {
    return fileId;
  }

  @Override
  public void setId(Integer id) {
    this.fileId = id;
  }

  public String getFilename() {
    return filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  public String getMimeType() {
    return mimeType;
  }

  public void setMimeType(String mimeType) {
    this.mimeType = mimeType;
  }

  public FileCategory getFileCategory() {
    return fileCategory;
  }

  public void setFileCategory(FileCategory fileCategory) {
    this.fileCategory = fileCategory;
  }

  @SuppressWarnings("PMD.MethodReturnsInternalArray")
  public byte[] getFileData() {
    return fileData;
  }

  @SuppressWarnings("PMD.ArrayIsStoredDirectly")
  public void setFileData(byte[] fileData) {
    this.fileData = fileData;
  }
}
