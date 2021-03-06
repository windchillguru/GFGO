/* bcwti
 *
 * Copyright (c) 2010 Parametric Technology Corporation (PTC). All Rights Reserved.
 *
 * This software is the confidential and proprietary information of PTC
 * and is subject to the terms of a software license agreement. You shall
 * not disclose such confidential information and shall use it only in accordance
 * with the terms of the license agreement.
 *
 * ecwti
 */
package wt.workflow.engine;

import wt.util.resource.*;

@RBUUID("wt.workflow.engine.WfCustomEventTypeResource")
@RBNameException //Grandfathered by conversion
public final class WfCustomEventTypeResource extends WTListResourceBundle {
   @RBEntry("Default")
   public static final String DEFAULT = "0";

   @RBEntry("CORRELATION LINK CHANGE")
   public static final String CORRELATION_LINK_CHANGE = "CORRELATION_LINK_CHANGE";
}