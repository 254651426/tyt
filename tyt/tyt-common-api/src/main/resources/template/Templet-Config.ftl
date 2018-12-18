<bean id="${clazzinfo.tableas}ReadService" class="${clazzinfo.serviceImplReadPackageName}.${clazzinfo.classname}ReadServiceImpl" />
<dubbo:service interface="${clazzinfo.serviceApiReadPackageName}.${clazzinfo.classname}ReadService" ref="${clazzinfo.tableas}ReadService" />
<bean id="${clazzinfo.tableas}WriteService" class="${clazzinfo.serviceImplWritePackageName}.${clazzinfo.classname}WriteServiceImpl" />
<dubbo:service interface="${clazzinfo.serviceApiWritePackageName}.${clazzinfo.classname}WriteService" ref="${clazzinfo.tableas}WriteService" />


<dubbo:reference id="${clazzinfo.tableas}ReadService" interface="${clazzinfo.serviceApiReadPackageName}.${clazzinfo.classname}ReadService" />
<dubbo:reference id="${clazzinfo.tableas}WriteService" interface="${clazzinfo.serviceApiWritePackageName}.${clazzinfo.classname}WriteService" />
