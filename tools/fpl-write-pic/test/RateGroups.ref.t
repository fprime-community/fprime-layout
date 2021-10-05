.PS

maxpswid=12.166666666666668
maxpsht=18.175

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

Instance_rateGroupDriverComp: instance("\fBrateGroupDriverComp\fR", 1.5, 2.5)
move down 0.2 from Instance_rateGroupDriverComp.s
move to Instance_rateGroupDriverComp.n + (4.833333333333334, 0)

Instance_rateGroup1Comp: instance("\fBrateGroup1Comp\fR", 1.5, 2.5)
move down 0.2 from Instance_rateGroup1Comp.s

Instance_rateGroup2Comp: instance("\fBrateGroup2Comp\fR", 1.5, 2.875)
move down 0.2 from Instance_rateGroup2Comp.s

Instance_rateGroup3Comp: instance("\fBrateGroup3Comp\fR", 1.5, 2.5)
move down 0.2 from Instance_rateGroup3Comp.s
move to Instance_rateGroup1Comp.n + (4.833333333333334, 0)

Instance_SG1: instance("\fBSG1\fR", 1.5, 1.625)
move down 0.2 from Instance_SG1.s

Instance_SG2: instance("\fBSG2\fR", 1.5, 1.625)
move down 0.2 from Instance_SG2.s

Instance_chanTlm: instance("\fBchanTlm\fR", 1.5, 1.625)
move down 0.2 from Instance_chanTlm.s

Instance_cmdSeq: instance("\fBcmdSeq\fR", 1.5, 1.625)
move down 0.2 from Instance_cmdSeq.s

Instance_sendBuffComp: instance("\fBsendBuffComp\fR", 1.5, 1.625)
move down 0.2 from Instance_sendBuffComp.s

Instance_SG3: instance("\fBSG3\fR", 1.5, 1.625)
move down 0.2 from Instance_SG3.s

Instance_SG4: instance("\fBSG4\fR", 1.5, 1.625)
move down 0.2 from Instance_SG4.s

Instance_health: instance("\fBhealth\fR", 1.5, 1.625)
move down 0.2 from Instance_health.s

Instance_SG5: instance("\fBSG5\fR", 1.5, 1.625)
move down 0.2 from Instance_SG5.s

Instance_blockDrv: instance("\fBblockDrv\fR", 1.5, 1.75)
move down 0.2 from Instance_blockDrv.s
move to Instance_SG1.n + (4.833333333333334, 0)

move to Instance_rateGroupDriverComp.nw - (0, 0.375)

move down 0.125
Port_rateGroupDriverComp_CycleIn_0: port("0")
" \fICycleIn\fR" at Port_rateGroupDriverComp_CycleIn_0.e ljust
move to Port_rateGroupDriverComp_CycleIn_0.s

move to Instance_rateGroupDriverComp.ne - (0, 1.25)

move down 0.125
Port_rateGroupDriverComp_CycleOut_0: port("0")
"\fICycleOut\fR " at Port_rateGroupDriverComp_CycleOut_0.w rjust
move to Port_rateGroupDriverComp_CycleOut_0.s
Port_rateGroupDriverComp_CycleOut_1: port("1") with .n at Port_rateGroupDriverComp_CycleOut_0.s
Port_rateGroupDriverComp_CycleOut_2: port("2") with .n at Port_rateGroupDriverComp_CycleOut_1.s

move to Instance_rateGroup1Comp.nw - (0, 0.375)

move down 0.125
Port_rateGroup1Comp_CycleIn_0: port("0")
" \fICycleIn\fR" at Port_rateGroup1Comp_CycleIn_0.e ljust
move to Port_rateGroup1Comp_CycleIn_0.s

move to Instance_rateGroup1Comp.ne - (0, 1.25)

move down 0.125
Port_rateGroup1Comp_RateGroupMemberOut_0: port("0")
"\fIRateGroupMemberOut\fR " at Port_rateGroup1Comp_RateGroupMemberOut_0.w rjust
move to Port_rateGroup1Comp_RateGroupMemberOut_0.s
Port_rateGroup1Comp_RateGroupMemberOut_1: port("1") with .n at Port_rateGroup1Comp_RateGroupMemberOut_0.s
Port_rateGroup1Comp_RateGroupMemberOut_2: port("2") with .n at Port_rateGroup1Comp_RateGroupMemberOut_1.s

move to Instance_rateGroup2Comp.nw - (0, 0.375)

move down 0.125
Port_rateGroup2Comp_CycleIn_0: port("0")
" \fICycleIn\fR" at Port_rateGroup2Comp_CycleIn_0.e ljust
move to Port_rateGroup2Comp_CycleIn_0.s

move to Instance_rateGroup2Comp.ne - (0, 1.25)

move down 0.125
Port_rateGroup2Comp_RateGroupMemberOut_0: port("0")
"\fIRateGroupMemberOut\fR " at Port_rateGroup2Comp_RateGroupMemberOut_0.w rjust
move to Port_rateGroup2Comp_RateGroupMemberOut_0.s
Port_rateGroup2Comp_RateGroupMemberOut_1: port("1") with .n at Port_rateGroup2Comp_RateGroupMemberOut_0.s
Port_rateGroup2Comp_RateGroupMemberOut_2: port("2") with .n at Port_rateGroup2Comp_RateGroupMemberOut_1.s
Port_rateGroup2Comp_RateGroupMemberOut_3: port("3") with .n at Port_rateGroup2Comp_RateGroupMemberOut_2.s

move to Instance_rateGroup3Comp.nw - (0, 0.375)

move down 0.125
Port_rateGroup3Comp_CycleIn_0: port("0")
" \fICycleIn\fR" at Port_rateGroup3Comp_CycleIn_0.e ljust
move to Port_rateGroup3Comp_CycleIn_0.s

move to Instance_rateGroup3Comp.ne - (0, 1.25)

move down 0.125
Port_rateGroup3Comp_RateGroupMemberOut_0: port("0")
"\fIRateGroupMemberOut\fR " at Port_rateGroup3Comp_RateGroupMemberOut_0.w rjust
move to Port_rateGroup3Comp_RateGroupMemberOut_0.s
Port_rateGroup3Comp_RateGroupMemberOut_1: port("1") with .n at Port_rateGroup3Comp_RateGroupMemberOut_0.s
Port_rateGroup3Comp_RateGroupMemberOut_2: port("2") with .n at Port_rateGroup3Comp_RateGroupMemberOut_1.s

move to Instance_SG1.nw - (0, 0.375)

move down 0.125
Port_SG1_schedIn_0: port("0")
" \fIschedIn\fR" at Port_SG1_schedIn_0.e ljust
move to Port_SG1_schedIn_0.s

move to Instance_SG1.ne - (0, 1.25)

move to Instance_SG2.nw - (0, 0.375)

move down 0.125
Port_SG2_schedIn_0: port("0")
" \fIschedIn\fR" at Port_SG2_schedIn_0.e ljust
move to Port_SG2_schedIn_0.s

move to Instance_SG2.ne - (0, 1.25)

move to Instance_chanTlm.nw - (0, 0.375)

move down 0.125
Port_chanTlm_Run_0: port("0")
" \fIRun\fR" at Port_chanTlm_Run_0.e ljust
move to Port_chanTlm_Run_0.s

move to Instance_chanTlm.ne - (0, 1.25)

move to Instance_cmdSeq.nw - (0, 0.375)

move down 0.125
Port_cmdSeq_schedIn_0: port("0")
" \fIschedIn\fR" at Port_cmdSeq_schedIn_0.e ljust
move to Port_cmdSeq_schedIn_0.s

move to Instance_cmdSeq.ne - (0, 1.25)

move to Instance_sendBuffComp.nw - (0, 0.375)

move down 0.125
Port_sendBuffComp_SchedIn_0: port("0")
" \fISchedIn\fR" at Port_sendBuffComp_SchedIn_0.e ljust
move to Port_sendBuffComp_SchedIn_0.s

move to Instance_sendBuffComp.ne - (0, 1.25)

move to Instance_SG3.nw - (0, 0.375)

move down 0.125
Port_SG3_schedIn_0: port("0")
" \fIschedIn\fR" at Port_SG3_schedIn_0.e ljust
move to Port_SG3_schedIn_0.s

move to Instance_SG3.ne - (0, 1.25)

move to Instance_SG4.nw - (0, 0.375)

move down 0.125
Port_SG4_schedIn_0: port("0")
" \fIschedIn\fR" at Port_SG4_schedIn_0.e ljust
move to Port_SG4_schedIn_0.s

move to Instance_SG4.ne - (0, 1.25)

move to Instance_health.nw - (0, 0.375)

move down 0.125
Port_health_Run_0: port("0")
" \fIRun\fR" at Port_health_Run_0.e ljust
move to Port_health_Run_0.s

move to Instance_health.ne - (0, 1.25)

move to Instance_SG5.nw - (0, 0.375)

move down 0.125
Port_SG5_schedIn_0: port("0")
" \fIschedIn\fR" at Port_SG5_schedIn_0.e ljust
move to Port_SG5_schedIn_0.s

move to Instance_SG5.ne - (0, 1.25)

move to Instance_blockDrv.nw - (0, 0.375)

move down 0.125
Port_blockDrv_Sched_0: port("0")
" \fISched\fR" at Port_blockDrv_Sched_0.e ljust
move to Port_blockDrv_Sched_0.s

move to Instance_blockDrv.ne - (0, 1.25)

move down 0.125
Port_blockDrv_CycleOut_0: port("0")
"\fICycleOut\fR " at Port_blockDrv_CycleOut_0.w rjust
move to Port_blockDrv_CycleOut_0.s

arrow from Port_rateGroup3Comp_RateGroupMemberOut_1.e to Port_SG5_schedIn_0.w
arrow from Port_rateGroup2Comp_RateGroupMemberOut_3.e to Port_SG4_schedIn_0.w
arrow from Port_rateGroup2Comp_RateGroupMemberOut_2.e to Port_SG3_schedIn_0.w
arrow from Port_rateGroup3Comp_RateGroupMemberOut_2.e to Port_blockDrv_Sched_0.w
arrow from Port_rateGroup3Comp_RateGroupMemberOut_0.e to Port_health_Run_0.w
arrow from Port_rateGroupDriverComp_CycleOut_1.e to Port_rateGroup2Comp_CycleIn_0.w
arrow from Port_rateGroup2Comp_RateGroupMemberOut_0.e to Port_cmdSeq_schedIn_0.w
arrow from Port_rateGroup1Comp_RateGroupMemberOut_0.e to Port_SG1_schedIn_0.w
arrow from Port_rateGroup1Comp_RateGroupMemberOut_1.e to Port_SG2_schedIn_0.w
arrow from Port_rateGroupDriverComp_CycleOut_0.e to Port_rateGroup1Comp_CycleIn_0.w
arrow from Port_rateGroup1Comp_RateGroupMemberOut_2.e to Port_chanTlm_Run_0.w
arrow from Port_rateGroupDriverComp_CycleOut_2.e to Port_rateGroup3Comp_CycleIn_0.w
arrow from Port_blockDrv_CycleOut_0.e to Port_rateGroupDriverComp_CycleIn_0.w dashed
arrow from Port_rateGroup2Comp_RateGroupMemberOut_1.e to Port_sendBuffComp_SchedIn_0.w
.PE
