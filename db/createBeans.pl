#!/bin/perl -w

exit unless defined($ARGV[0]);

sub createTerrainBean($) {

	my $n = shift;
	my $i = 1;
	my $bean = '
	<bean id="terrain" class="ranball.domain.Terrain">
	    <property name="cells">
    		<list>
		    ';
			
	foreach (1..$n) {
		$bean.="    <list>\n    		    ";
		foreach (1..$n) {
			$bean.="    <ref bean=\"cell$i\" />\n    		    ";
			$i++;
		}
		$bean.="</list>\n		    ";
	}
	$bean.='</list>
	    </property>
	</bean>    
    
	';
}

sub createCellBean($) {
    "    <bean id=\"cell$_[0]\" class=\"ranball.domain.Cell\">
    	<property name=\"id\" value=\"$_[0]\"/>
    	<property name=\"type\" value=\"".(int rand(7))."\"/>
    	<property name=\"coord\" value=\"$_[0]\"/>
    </bean>
	
"
}

my $result = '<?xml version="1.0" encoding="UTF-8"?>

<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans-2.5.xsd">
	   ';
	   
$result.= createTerrainBean($ARGV[0]);
$result.= createCellBean($_) foreach (1..($ARGV[0]*$ARGV[0]));
$result.= '</beans>';
print $result;
