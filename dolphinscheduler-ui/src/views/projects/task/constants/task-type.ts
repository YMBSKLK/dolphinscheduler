/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
export type TaskType =
  | 'SHELL'
  | 'SUB_PROCESS'
  | 'PROCEDURE'
  | 'SQL'
  | 'SPARK'
  | 'FLINK'
  | 'MR'
  | 'PYTHON'
  | 'DEPENDENT'
  | 'HTTP'
  | 'HTTP_ASSET_BACKUP'
  | 'HTTP_ASSET_ARCHIVE'
  | 'HTTP_ASSET_RECOVERY'
  | 'HTTP_ASSESS_EXECUTE'
  | 'DATAX'
  | 'PIGEON'
  | 'SQOOP'
  | 'CONDITIONS'
  | 'DATA_QUALITY'
  | 'SWITCH'
  | 'SEATUNNEL'
  | 'ASSET_BACKUP'
  | 'ASSET_ARCHIVE'
  | 'ASSET_RECOVERY'
  | 'ETL'
  | 'QUALITY'
  | 'EMR'
  | 'ZEPPELIN'
  | 'K8S'
  | 'JUPYTER'
  | 'MLFLOW'
  | 'OPENMLDB'
  | 'DVC'
  | 'JAVA'
  | 'DINKY'
  | 'SAGEMAKER'
  | 'CHUNJUN'
  | 'FLINK_STREAM'
  | 'PYTORCH'
  | 'HIVECLI'
  | 'DMS'
  | 'DATASYNC'
  | 'KUBEFLOW'
  | 'LINKIS'

export type TaskExecuteType = 'STREAM' | 'BATCH'

export const TASK_TYPES_MAP = {
  JAVA: {
    alias: 'JAVA'
  },
  SHELL: {
    alias: 'SHELL'
  },
  SUB_PROCESS: {
    alias: 'SUB_PROCESS'
  },
  PROCEDURE: {
    alias: 'PROCEDURE'
  },
  SQL: {
    alias: 'SQL'
  },
  SPARK: {
    alias: 'SPARK'
  },
  FLINK: {
    alias: 'FLINK'
  },
  MR: {
    alias: 'MapReduce',
    helperLinkDisable: true
  },
  PYTHON: {
    alias: 'PYTHON'
  },
  DEPENDENT: {
    alias: 'DEPENDENT'
  },
  HTTP: {
    alias: 'HTTP'
  },
  HTTP_ASSET_BACKUP: {
    alias: 'HTTP_ASSET_BACKUP'
  },
  HTTP_ASSET_ARCHIVE: {
    alias: 'HTTP_ASSET_ARCHIVE'
  },
  HTTP_ASSET_RECOVERY: {
    alias: 'HTTP_ASSET_RECOVERY'
  },
  HTTP_ASSESS_EXECUTE: {
    alias: 'HTTP_ASSESS_EXECUTE'
  },
  DATAX: {
    alias: 'DataX'
  },
  PIGEON: {
    alias: 'PIGEON'
  },
  SQOOP: {
    alias: 'SQOOP',
    helperLinkDisable: true
  },
  CONDITIONS: {
    alias: 'CONDITIONS'
  },
  DATA_QUALITY: {
    alias: 'DATA_QUALITY',
    helperLinkDisable: true
  },
  SWITCH: {
    alias: 'SWITCH'
  },
  SEATUNNEL: {
    alias: 'SeaTunnel',
    helperLinkDisable: true
  },
  ASSET_BACKUP: {
    alias: 'ASSET_BACKUP',
    helperLinkDisable: true
  },
  ASSET_ARCHIVE: {
    alias: 'ASSET_ARCHIVE',
    helperLinkDisable: true
  },
  ASSET_RECOVERY: {
    alias: 'ASSET_RECOVERY',
    helperLinkDisable: true
  },
  ETL: {
    alias: 'ETL',
    helperLinkDisable: true
  },
  QUALITY: {
    alias: 'Quality',
    helperLinkDisable: true
  },
  EMR: {
    alias: 'AmazonEMR',
    helperLinkDisable: true
  },
  ZEPPELIN: {
    alias: 'ZEPPELIN',
    helperLinkDisable: true
  },
  JUPYTER: {
    alias: 'JUPYTER',
    helperLinkDisable: true
  },
  K8S: {
    alias: 'K8S',
    helperLinkDisable: true
  },
  MLFLOW: {
    alias: 'MLFLOW',
    helperLinkDisable: true
  },
  OPENMLDB: {
    alias: 'OPENMLDB',
    helperLinkDisable: true
  },
  DVC: {
    alias: 'DVC',
    helperLinkDisable: true
  },
  DINKY: {
    alias: 'DINKY',
    helperLinkDisable: true
  },
  SAGEMAKER: {
    alias: 'SageMaker',
    helperLinkDisable: true
  },
  CHUNJUN: {
    alias: 'CHUNJUN',
    helperLinkDisable: true
  },
  FLINK_STREAM: {
    alias: 'FLINK_STREAM',
    helperLinkDisable: true,
    taskExecuteType: 'STREAM'
  },
  PYTORCH: {
    alias: 'Pytorch',
    helperLinkDisable: true
  },
  HIVECLI: {
    alias: 'HIVECLI',
    helperLinkDisable: true
  },
  DMS: {
    alias: 'DMS',
    helperLinkDisable: true
  },
  DATASYNC: {
    alias: 'DATASYNC',
    helperLinkDisable: true
  },
  KUBEFLOW: {
    alias: 'KUBEFLOW',
    helperLinkDisable: true
  },
  LINKIS: {
    alias: 'LINKIS',
    helperLinkDisable: true
  }
} as {
  [key in TaskType]: {
    alias: string
    helperLinkDisable?: boolean
    taskExecuteType?: TaskExecuteType
  }
}
