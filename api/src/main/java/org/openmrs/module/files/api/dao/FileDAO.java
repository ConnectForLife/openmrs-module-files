/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 *  graphic logo is a trademark of OpenMRS Inc.
 */

package org.openmrs.module.files.api.dao;

import org.hibernate.criterion.Restrictions;
import org.openmrs.module.files.File;
import org.openmrs.module.files.FileCategory;

import java.util.List;

public class FileDAO extends BaseFilesOpenmrsObjectDAO<File> {
  FileDAO() {
    super(File.class, File.VOIDED_PROP);
  }

  public File getFile(Integer fileId) {
    return internalRead(fileId);
  }

  public File getFileByUuid(String uuid) {
    return internalReadByUuid(uuid);
  }

  public File getFileByFilenameAndCategory(String filename, FileCategory category) {
    return (File) getSession()
        .createCriteria(File.class)
        .add(Restrictions.eq(File.FILENAME_PROP, filename))
        .add(Restrictions.eq(File.FILE_CATEGORY_PROP, category))
        .uniqueResult();
  }

  public List<File> getAllFiles(boolean includeVoided) {
    return internalReadAll(includeVoided);
  }

  public long getFileCount(boolean includeVoided) {
    return internalCountAll(includeVoided);
  }

  public File saveFile(File file) {
    return internalSave(file);
  }

  public void deleteFile(File file) {
    internalDelete(file);
  }
}
