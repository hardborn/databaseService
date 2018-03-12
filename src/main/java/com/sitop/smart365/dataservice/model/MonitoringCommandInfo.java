package com.sitop.smart365.dataservice.model;

import com.alibaba.fastjson.annotation.JSONField;

public class MonitoringCommandInfo {
    @JSONField(name = "nCmdId")
    private int commandId;
    @JSONField(name = "nCmdAddress")
   private int  commandAddress;
    @JSONField(name = "nCmdFunCode")
   private int commandCode;
    @JSONField(name = "nCmdRegAddress")
   private int registerAddress;
    @JSONField(name = "nCmdRegLength")
   private int registerLength;
    @JSONField(name = "nCmdType")
   private int dataType;

   public int getDataType() {
      return dataType;
   }

   public void setDataType(int dataType) {
      this.dataType = dataType;
   }

   public int getRegisterLength() {
      return registerLength;
   }

   public void setRegisterLength(int registerLength) {
      this.registerLength = registerLength;
   }

   public int getRegisterAddress() {
      return registerAddress;
   }

   public void setRegisterAddress(int registerAddress) {
      this.registerAddress = registerAddress;
   }

   public int getCommandCode() {
      return commandCode;
   }

   public void setCommandCode(int commandCode) {
      this.commandCode = commandCode;
   }

   public int getCommandAddress() {
      return commandAddress;
   }

   public void setCommandAddress(int commandAddress) {
      this.commandAddress = commandAddress;
   }

   public int getCommandId() {
      return commandId;
   }

   public void setCommandId(int commandId) {
      this.commandId = commandId;
   }
}
