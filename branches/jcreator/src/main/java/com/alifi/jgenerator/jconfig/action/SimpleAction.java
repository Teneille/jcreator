package com.alifi.jgenerator.jconfig.action;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.FormGroup;
import com.alifi.jgenerator.model.SimpleObject;

public class SimpleAction {
    public void doGreeting(@FormGroup("simple") SimpleObject simple, Context context, Navigator nav) {
        String name = simple.getName();
        
        nav.redirectTo("jconfigModule").withTarget("hello").withParameter("name", name);
    }
}
