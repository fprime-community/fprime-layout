.PS

maxpswid=9.0
maxpsht=23.525

define label { [
  box invis height 0.375 $1
] }
define instance { [
  B: box invis $1 width $2 height 0.375
  box width $2 height $3 shaded "skyblue" with .n at B.s
] }
define port { [
  box $1 width 0.375 height 0.375 shaded "white"
] }

Instance_eventLogger: instance("\fBeventLogger\fR", 1.5, 1.625)
move down 0.2 from Instance_eventLogger.s

Instance_health: instance("\fBhealth\fR", 1.5, 1.625)
move down 0.2 from Instance_health.s

Instance_prmDb: instance("\fBprmDb\fR", 1.5, 1.625)
move down 0.2 from Instance_prmDb.s

Instance_cmdSeq: instance("\fBcmdSeq\fR", 1.5, 1.625)
move down 0.2 from Instance_cmdSeq.s

Instance_fileDownlink: instance("\fBfileDownlink\fR", 1.5, 1.625)
move down 0.2 from Instance_fileDownlink.s

Instance_sendBuffComp: instance("\fBsendBuffComp\fR", 1.5, 1.625)
move down 0.2 from Instance_sendBuffComp.s

Instance_recvBuffComp: instance("\fBrecvBuffComp\fR", 1.5, 1.625)
move down 0.2 from Instance_recvBuffComp.s

Instance_SG1: instance("\fBSG1\fR", 1.5, 1.625)
move down 0.2 from Instance_SG1.s

Instance_SG2: instance("\fBSG2\fR", 1.5, 1.625)
move down 0.2 from Instance_SG2.s

Instance_SG3: instance("\fBSG3\fR", 1.5, 1.625)
move down 0.2 from Instance_SG3.s

Instance_SG4: instance("\fBSG4\fR", 1.5, 1.625)
move down 0.2 from Instance_SG4.s

Instance_SG5: instance("\fBSG5\fR", 1.5, 1.625)
move down 0.2 from Instance_SG5.s

Instance_pingRcvr: instance("\fBpingRcvr\fR", 1.5, 1.625)
move down 0.2 from Instance_pingRcvr.s
move to Instance_eventLogger.n + (6.5, 0)

Instance_cmdDisp: instance("\fBcmdDisp\fR", 1.5, 6.625)
move down 0.2 from Instance_cmdDisp.s
move to Instance_cmdDisp.n + (6.5, 0)

move to Instance_eventLogger.nw - (0, 0.375)

move to Instance_eventLogger.ne - (0, 1.125)

move down 0.125
Port_eventLogger_CmdReg_0: port("0")
"\fICmdReg\fR " at Port_eventLogger_CmdReg_0.w rjust
move to Port_eventLogger_CmdReg_0.s

move to Instance_health.nw - (0, 0.375)

move to Instance_health.ne - (0, 1.125)

move down 0.125
Port_health_CmdReg_0: port("0")
"\fICmdReg\fR " at Port_health_CmdReg_0.w rjust
move to Port_health_CmdReg_0.s

move to Instance_prmDb.nw - (0, 0.375)

move to Instance_prmDb.ne - (0, 1.125)

move down 0.125
Port_prmDb_CmdReg_0: port("0")
"\fICmdReg\fR " at Port_prmDb_CmdReg_0.w rjust
move to Port_prmDb_CmdReg_0.s

move to Instance_cmdSeq.nw - (0, 0.375)

move to Instance_cmdSeq.ne - (0, 1.125)

move down 0.125
Port_cmdSeq_cmdRegOut_0: port("0")
"\fIcmdRegOut\fR " at Port_cmdSeq_cmdRegOut_0.w rjust
move to Port_cmdSeq_cmdRegOut_0.s

move to Instance_fileDownlink.nw - (0, 0.375)

move to Instance_fileDownlink.ne - (0, 1.125)

move down 0.125
Port_fileDownlink_cmdRegOut_0: port("0")
"\fIcmdRegOut\fR " at Port_fileDownlink_cmdRegOut_0.w rjust
move to Port_fileDownlink_cmdRegOut_0.s

move to Instance_sendBuffComp.nw - (0, 0.375)

move to Instance_sendBuffComp.ne - (0, 1.125)

move down 0.125
Port_sendBuffComp_CmdReg_0: port("0")
"\fICmdReg\fR " at Port_sendBuffComp_CmdReg_0.w rjust
move to Port_sendBuffComp_CmdReg_0.s

move to Instance_recvBuffComp.nw - (0, 0.375)

move to Instance_recvBuffComp.ne - (0, 1.125)

move down 0.125
Port_recvBuffComp_CmdReg_0: port("0")
"\fICmdReg\fR " at Port_recvBuffComp_CmdReg_0.w rjust
move to Port_recvBuffComp_CmdReg_0.s

move to Instance_SG1.nw - (0, 0.375)

move to Instance_SG1.ne - (0, 1.125)

move down 0.125
Port_SG1_cmdRegOut_0: port("0")
"\fIcmdRegOut\fR " at Port_SG1_cmdRegOut_0.w rjust
move to Port_SG1_cmdRegOut_0.s

move to Instance_SG2.nw - (0, 0.375)

move to Instance_SG2.ne - (0, 1.125)

move down 0.125
Port_SG2_cmdRegOut_0: port("0")
"\fIcmdRegOut\fR " at Port_SG2_cmdRegOut_0.w rjust
move to Port_SG2_cmdRegOut_0.s

move to Instance_SG3.nw - (0, 0.375)

move to Instance_SG3.ne - (0, 1.125)

move down 0.125
Port_SG3_cmdRegOut_0: port("0")
"\fIcmdRegOut\fR " at Port_SG3_cmdRegOut_0.w rjust
move to Port_SG3_cmdRegOut_0.s

move to Instance_SG4.nw - (0, 0.375)

move to Instance_SG4.ne - (0, 1.125)

move down 0.125
Port_SG4_cmdRegOut_0: port("0")
"\fIcmdRegOut\fR " at Port_SG4_cmdRegOut_0.w rjust
move to Port_SG4_cmdRegOut_0.s

move to Instance_SG5.nw - (0, 0.375)

move to Instance_SG5.ne - (0, 1.125)

move down 0.125
Port_SG5_cmdRegOut_0: port("0")
"\fIcmdRegOut\fR " at Port_SG5_cmdRegOut_0.w rjust
move to Port_SG5_cmdRegOut_0.s

move to Instance_pingRcvr.nw - (0, 0.375)

move to Instance_pingRcvr.ne - (0, 1.125)

move down 0.125
Port_pingRcvr_CmdReg_0: port("0")
"\fICmdReg\fR " at Port_pingRcvr_CmdReg_0.w rjust
move to Port_pingRcvr_CmdReg_0.s

move to Instance_cmdDisp.nw - (0, 0.375)

move down 0.125
Port_cmdDisp_compCmdReg_0: port("0")
" \fIcompCmdReg\fR" at Port_cmdDisp_compCmdReg_0.e ljust
move to Port_cmdDisp_compCmdReg_0.s
Port_cmdDisp_compCmdReg_1: port("1") with .n at Port_cmdDisp_compCmdReg_0.s
Port_cmdDisp_compCmdReg_2: port("2") with .n at Port_cmdDisp_compCmdReg_1.s
Port_cmdDisp_compCmdReg_3: port("3") with .n at Port_cmdDisp_compCmdReg_2.s
Port_cmdDisp_compCmdReg_4: port("4") with .n at Port_cmdDisp_compCmdReg_3.s
Port_cmdDisp_compCmdReg_5: port("5") with .n at Port_cmdDisp_compCmdReg_4.s
Port_cmdDisp_compCmdReg_6: port("6") with .n at Port_cmdDisp_compCmdReg_5.s
Port_cmdDisp_compCmdReg_7: port("7") with .n at Port_cmdDisp_compCmdReg_6.s
Port_cmdDisp_compCmdReg_8: port("8") with .n at Port_cmdDisp_compCmdReg_7.s
Port_cmdDisp_compCmdReg_9: port("9") with .n at Port_cmdDisp_compCmdReg_8.s
Port_cmdDisp_compCmdReg_10: port("10") with .n at Port_cmdDisp_compCmdReg_9.s
Port_cmdDisp_compCmdReg_11: port("11") with .n at Port_cmdDisp_compCmdReg_10.s
Port_cmdDisp_compCmdReg_12: port("12") with .n at Port_cmdDisp_compCmdReg_11.s
Port_cmdDisp_compCmdReg_13: port("13") with .n at Port_cmdDisp_compCmdReg_12.s

move to Instance_cmdDisp.ne - (0, 6.125)

move down 0.125
Port_cmdDisp_CmdReg_0: port("0")
"\fICmdReg\fR " at Port_cmdDisp_CmdReg_0.w rjust
move to Port_cmdDisp_CmdReg_0.s

arrow from Port_SG5_cmdRegOut_0.e to Port_cmdDisp_compCmdReg_12.w
arrow from Port_SG2_cmdRegOut_0.e to Port_cmdDisp_compCmdReg_9.w
arrow from Port_SG1_cmdRegOut_0.e to Port_cmdDisp_compCmdReg_8.w
arrow from Port_pingRcvr_CmdReg_0.e to Port_cmdDisp_compCmdReg_13.w
arrow from Port_SG4_cmdRegOut_0.e to Port_cmdDisp_compCmdReg_11.w
arrow from Port_fileDownlink_cmdRegOut_0.e to Port_cmdDisp_compCmdReg_5.w
arrow from Port_sendBuffComp_CmdReg_0.e to Port_cmdDisp_compCmdReg_6.w
arrow from Port_health_CmdReg_0.e to Port_cmdDisp_compCmdReg_2.w
arrow from Port_prmDb_CmdReg_0.e to Port_cmdDisp_compCmdReg_3.w
arrow from Port_cmdDisp_CmdReg_0.e to Port_cmdDisp_compCmdReg_1.w dashed
arrow from Port_cmdSeq_cmdRegOut_0.e to Port_cmdDisp_compCmdReg_4.w
arrow from Port_SG3_cmdRegOut_0.e to Port_cmdDisp_compCmdReg_10.w
arrow from Port_eventLogger_CmdReg_0.e to Port_cmdDisp_compCmdReg_0.w
arrow from Port_recvBuffComp_CmdReg_0.e to Port_cmdDisp_compCmdReg_7.w
.PE
