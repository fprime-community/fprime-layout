.PS

maxpswid=9.0
maxpsht=34.475

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

Instance_blockDrv: instance("\fBblockDrv\fR", 1.5, 1.625)
move down 0.2 from Instance_blockDrv.s

Instance_cmdDisp: instance("\fBcmdDisp\fR", 1.5, 1.625)
move down 0.2 from Instance_cmdDisp.s

Instance_cmdSeq: instance("\fBcmdSeq\fR", 1.5, 1.625)
move down 0.2 from Instance_cmdSeq.s

Instance_fileDownlink: instance("\fBfileDownlink\fR", 1.5, 1.625)
move down 0.2 from Instance_fileDownlink.s

Instance_fileDownlinkBufferManager: instance("\fBfileDownlinkBufferManager\fR", 1.5, 1.625)
move down 0.2 from Instance_fileDownlinkBufferManager.s

Instance_fileUplink: instance("\fBfileUplink\fR", 1.5, 1.625)
move down 0.2 from Instance_fileUplink.s

Instance_fileUplinkBufferManager: instance("\fBfileUplinkBufferManager\fR", 1.5, 1.625)
move down 0.2 from Instance_fileUplinkBufferManager.s

Instance_health: instance("\fBhealth\fR", 1.5, 1.625)
move down 0.2 from Instance_health.s

Instance_pingRcvr: instance("\fBpingRcvr\fR", 1.5, 1.625)
move down 0.2 from Instance_pingRcvr.s

Instance_rateGroup1Comp: instance("\fBrateGroup1Comp\fR", 1.5, 1.625)
move down 0.2 from Instance_rateGroup1Comp.s

Instance_rateGroup2Comp: instance("\fBrateGroup2Comp\fR", 1.5, 1.625)
move down 0.2 from Instance_rateGroup2Comp.s

Instance_rateGroup3Comp: instance("\fBrateGroup3Comp\fR", 1.5, 1.625)
move down 0.2 from Instance_rateGroup3Comp.s

Instance_recvBuffComp: instance("\fBrecvBuffComp\fR", 1.5, 1.625)
move down 0.2 from Instance_recvBuffComp.s

Instance_sendBuffComp: instance("\fBsendBuffComp\fR", 1.5, 1.625)
move down 0.2 from Instance_sendBuffComp.s

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
move to Instance_blockDrv.n + (6.5, 0)

Instance_chanTlm: instance("\fBchanTlm\fR", 1.5, 1.625)
move down 0.2 from Instance_chanTlm.s
move to Instance_chanTlm.n + (6.5, 0)

move to Instance_blockDrv.nw - (0, 0.375)

move to Instance_blockDrv.ne - (0, 1.125)

move down 0.125
Port_blockDrv_Tlm_0: port("0")
"\fITlm\fR " at Port_blockDrv_Tlm_0.w rjust
move to Port_blockDrv_Tlm_0.s

move to Instance_cmdDisp.nw - (0, 0.375)

move to Instance_cmdDisp.ne - (0, 1.125)

move down 0.125
Port_cmdDisp_Tlm_0: port("0")
"\fITlm\fR " at Port_cmdDisp_Tlm_0.w rjust
move to Port_cmdDisp_Tlm_0.s

move to Instance_cmdSeq.nw - (0, 0.375)

move to Instance_cmdSeq.ne - (0, 1.125)

move down 0.125
Port_cmdSeq_tlmOut_0: port("0")
"\fItlmOut\fR " at Port_cmdSeq_tlmOut_0.w rjust
move to Port_cmdSeq_tlmOut_0.s

move to Instance_fileDownlink.nw - (0, 0.375)

move to Instance_fileDownlink.ne - (0, 1.125)

move down 0.125
Port_fileDownlink_tlmOut_0: port("0")
"\fItlmOut\fR " at Port_fileDownlink_tlmOut_0.w rjust
move to Port_fileDownlink_tlmOut_0.s

move to Instance_fileDownlinkBufferManager.nw - (0, 0.375)

move to Instance_fileDownlinkBufferManager.ne - (0, 1.125)

move down 0.125
Port_fileDownlinkBufferManager_tlmOut_0: port("0")
"\fItlmOut\fR " at Port_fileDownlinkBufferManager_tlmOut_0.w rjust
move to Port_fileDownlinkBufferManager_tlmOut_0.s

move to Instance_fileUplink.nw - (0, 0.375)

move to Instance_fileUplink.ne - (0, 1.125)

move down 0.125
Port_fileUplink_tlmOut_0: port("0")
"\fItlmOut\fR " at Port_fileUplink_tlmOut_0.w rjust
move to Port_fileUplink_tlmOut_0.s

move to Instance_fileUplinkBufferManager.nw - (0, 0.375)

move to Instance_fileUplinkBufferManager.ne - (0, 1.125)

move down 0.125
Port_fileUplinkBufferManager_tlmOut_0: port("0")
"\fItlmOut\fR " at Port_fileUplinkBufferManager_tlmOut_0.w rjust
move to Port_fileUplinkBufferManager_tlmOut_0.s

move to Instance_health.nw - (0, 0.375)

move to Instance_health.ne - (0, 1.125)

move down 0.125
Port_health_Tlm_0: port("0")
"\fITlm\fR " at Port_health_Tlm_0.w rjust
move to Port_health_Tlm_0.s

move to Instance_pingRcvr.nw - (0, 0.375)

move to Instance_pingRcvr.ne - (0, 1.125)

move down 0.125
Port_pingRcvr_Tlm_0: port("0")
"\fITlm\fR " at Port_pingRcvr_Tlm_0.w rjust
move to Port_pingRcvr_Tlm_0.s

move to Instance_rateGroup1Comp.nw - (0, 0.375)

move to Instance_rateGroup1Comp.ne - (0, 1.125)

move down 0.125
Port_rateGroup1Comp_Tlm_0: port("0")
"\fITlm\fR " at Port_rateGroup1Comp_Tlm_0.w rjust
move to Port_rateGroup1Comp_Tlm_0.s

move to Instance_rateGroup2Comp.nw - (0, 0.375)

move to Instance_rateGroup2Comp.ne - (0, 1.125)

move down 0.125
Port_rateGroup2Comp_Tlm_0: port("0")
"\fITlm\fR " at Port_rateGroup2Comp_Tlm_0.w rjust
move to Port_rateGroup2Comp_Tlm_0.s

move to Instance_rateGroup3Comp.nw - (0, 0.375)

move to Instance_rateGroup3Comp.ne - (0, 1.125)

move down 0.125
Port_rateGroup3Comp_Tlm_0: port("0")
"\fITlm\fR " at Port_rateGroup3Comp_Tlm_0.w rjust
move to Port_rateGroup3Comp_Tlm_0.s

move to Instance_recvBuffComp.nw - (0, 0.375)

move to Instance_recvBuffComp.ne - (0, 1.125)

move down 0.125
Port_recvBuffComp_Tlm_0: port("0")
"\fITlm\fR " at Port_recvBuffComp_Tlm_0.w rjust
move to Port_recvBuffComp_Tlm_0.s

move to Instance_sendBuffComp.nw - (0, 0.375)

move to Instance_sendBuffComp.ne - (0, 1.125)

move down 0.125
Port_sendBuffComp_Tlm_0: port("0")
"\fITlm\fR " at Port_sendBuffComp_Tlm_0.w rjust
move to Port_sendBuffComp_Tlm_0.s

move to Instance_SG1.nw - (0, 0.375)

move to Instance_SG1.ne - (0, 1.125)

move down 0.125
Port_SG1_tlmOut_0: port("0")
"\fItlmOut\fR " at Port_SG1_tlmOut_0.w rjust
move to Port_SG1_tlmOut_0.s

move to Instance_SG2.nw - (0, 0.375)

move to Instance_SG2.ne - (0, 1.125)

move down 0.125
Port_SG2_tlmOut_0: port("0")
"\fItlmOut\fR " at Port_SG2_tlmOut_0.w rjust
move to Port_SG2_tlmOut_0.s

move to Instance_SG3.nw - (0, 0.375)

move to Instance_SG3.ne - (0, 1.125)

move down 0.125
Port_SG3_tlmOut_0: port("0")
"\fItlmOut\fR " at Port_SG3_tlmOut_0.w rjust
move to Port_SG3_tlmOut_0.s

move to Instance_SG4.nw - (0, 0.375)

move to Instance_SG4.ne - (0, 1.125)

move down 0.125
Port_SG4_tlmOut_0: port("0")
"\fItlmOut\fR " at Port_SG4_tlmOut_0.w rjust
move to Port_SG4_tlmOut_0.s

move to Instance_SG5.nw - (0, 0.375)

move to Instance_SG5.ne - (0, 1.125)

move down 0.125
Port_SG5_tlmOut_0: port("0")
"\fItlmOut\fR " at Port_SG5_tlmOut_0.w rjust
move to Port_SG5_tlmOut_0.s

move to Instance_chanTlm.nw - (0, 0.375)

move down 0.125
Port_chanTlm_TlmRecv_0: port("0")
" \fITlmRecv\fR" at Port_chanTlm_TlmRecv_0.e ljust
move to Port_chanTlm_TlmRecv_0.s

move to Instance_chanTlm.ne - (0, 1.25)

arrow from Port_blockDrv_Tlm_0.e to Port_chanTlm_TlmRecv_0.w
arrow from Port_recvBuffComp_Tlm_0.e to Port_chanTlm_TlmRecv_0.w
arrow from Port_pingRcvr_Tlm_0.e to Port_chanTlm_TlmRecv_0.w
arrow from Port_rateGroup1Comp_Tlm_0.e to Port_chanTlm_TlmRecv_0.w
arrow from Port_rateGroup2Comp_Tlm_0.e to Port_chanTlm_TlmRecv_0.w
arrow from Port_fileUplink_tlmOut_0.e to Port_chanTlm_TlmRecv_0.w
arrow from Port_health_Tlm_0.e to Port_chanTlm_TlmRecv_0.w
arrow from Port_SG2_tlmOut_0.e to Port_chanTlm_TlmRecv_0.w
arrow from Port_SG3_tlmOut_0.e to Port_chanTlm_TlmRecv_0.w
arrow from Port_fileDownlinkBufferManager_tlmOut_0.e to Port_chanTlm_TlmRecv_0.w
arrow from Port_rateGroup3Comp_Tlm_0.e to Port_chanTlm_TlmRecv_0.w
arrow from Port_cmdDisp_Tlm_0.e to Port_chanTlm_TlmRecv_0.w
arrow from Port_fileUplinkBufferManager_tlmOut_0.e to Port_chanTlm_TlmRecv_0.w
arrow from Port_SG1_tlmOut_0.e to Port_chanTlm_TlmRecv_0.w
arrow from Port_cmdSeq_tlmOut_0.e to Port_chanTlm_TlmRecv_0.w
arrow from Port_sendBuffComp_Tlm_0.e to Port_chanTlm_TlmRecv_0.w
arrow from Port_SG5_tlmOut_0.e to Port_chanTlm_TlmRecv_0.w
arrow from Port_fileDownlink_tlmOut_0.e to Port_chanTlm_TlmRecv_0.w
arrow from Port_SG4_tlmOut_0.e to Port_chanTlm_TlmRecv_0.w
.PE
