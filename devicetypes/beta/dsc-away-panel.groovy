/**
 *  DSC Away Panel
 *
 *  Author: Ralph Torchia
 *  Original Code By: Jordan <jordan@xeron.cc>, Rob Fisher <robfish@att.net>, Carlos Santiago <carloss66@gmail.com>, JTT <aesystems@gmail.com>
 *  Date: 2016-02-03
 */

// for the UI
metadata {
    definition (
    	name: "DSC Away Panel",
        author: "Ralph Torchia",
        namespace: 'dsc',
        ocfDeviceType: "oic.d.securitypanel",
        mnmn: "SmartThingsCommunity",
        vid: "b5b2726f-6db4-3759-b10f-7b279b8724e1"
	)
    {
        capability "Switch"
        capability "Refresh"
        capability "pizzafiber16443.partitionStatus"
        capability "pizzafiber16443.partitionCommands"
        
        attribute "status", "string"
        attribute "trouble", "string"
        attribute "chime", "string"
        attribute "ledready", "string"
        attribute "ledarmed", "string"
        attribute "ledmemory", "string"
        attribute "ledbypass", "string"
        attribute "ledtrouble", "string"
        attribute "ledprogram", "string"
        attribute "ledfire", "string"
        attribute "ledbacklight", "string"
        
        command "away"
        command "autobypass"
        command "bypassoff"
        command "disarm"
        command "instant"
        command "night"
        command "nokey"
        command "partition"
        command "key"
        command "keyfire"
        command "keyaux"
        command "keypanic"
        command "reset"
        command "stay"
        command "togglechime"
 
    }

    // simulator metadata
    simulator {
    }

    // UI tile definitions
    tiles(scale: 2) {
      multiAttributeTile(name:"status", type: "generic", width: 6, height: 4){
        tileAttribute ("device.status", key: "PRIMARY_CONTROL") {
          attributeState "alarm", label:'Alarm', action: 'disarm', icon:"st.security.alarm.alarm", backgroundColor:"#ff0000"
          attributeState "away", label:'Armed Away', action: 'disarm', icon:"st.security.alarm.on", backgroundColor:"#800000"
          attributeState "disarm", label:'Disarmed', icon:"st.security.alarm.off", backgroundColor:"#79b821"
          attributeState "duress", label:'Duress', action: 'disarm', icon:"st.security.alarm.alarm", backgroundColor:"#ff0000"
          attributeState "entrydelay", label:'Entry Delay', action: 'disarm', icon:"st.security.alarm.on", backgroundColor:"#ff9900"
          attributeState "exitdelay", label:'Exit Delay', action: 'disarm', icon:"st.security.alarm.on", backgroundColor:"#ff9900"
          attributeState "notready", label:'Not Ready', icon:"st.security.alarm.off", backgroundColor:"#ffcc00"
          attributeState "ready", label:'Ready', action: 'away', icon:"st.security.alarm.off", backgroundColor:"#79b821"
          attributeState "forceready", label:'Ready - F', action: 'away', icon:"st.security.alarm.off", backgroundColor:"#79b821"
          attributeState "stay", label:'Armed Stay', action: 'disarm', icon:"st.security.alarm.on", backgroundColor:"#008CC1"
          attributeState "instantaway", label:'Armed Instant Away', action: 'disarm', icon:"st.security.alarm.on", backgroundColor:"#800000"
          attributeState "instantstay", label:'Armed Instant Stay', action: 'disarm', icon:"st.security.alarm.on", backgroundColor:"#008CC1"
        }
        tileAttribute ("device.trouble", key: "SECONDARY_CONTROL") {
          attributeState "detected", label: 'Trouble', icon: "st.security.alarm.alarm"
          attributeState "clear", label: 'No Trouble', icon: "st.security.alarm.clear"
        }
      }
      standardTile("disarm", "capability.momentary", width: 2, height: 2, title: "Disarm", decoration:"flat"){
        state "disarm", label: 'Disarm', action: "disarm", icon: "st.presence.house.unlocked", backgroundColor: "#79b821"
      }
      standardTile("away", "capability.momentary", width: 2, height: 2, title: "Away", decoration:"flat"){
        state "away", label: 'Away', action: "away", icon: "st.presence.car.car", backgroundColor: "#800000"
      }
      standardTile("stay", "capability.momentary", width: 2, height: 2, title: "Stay", decoration:"flat"){
        state "stay", label: 'Stay', action: "stay", icon: "st.presence.house.secured", backgroundColor: "#008CC1"
      }
      standardTile("refresh", "device.refresh", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
        state "default", action:"refresh.refresh", icon:"st.secondary.refresh"
      }
      standardTile("chime", "device.chime", width: 2, height: 2, title: "Chime", decoration:"flat"){
        state "togglechime", label: 'Toggling Chime', action: "togglechime", icon: "st.alarm.beep.beep"
        state "chime", label: 'Chime', action: "togglechime", icon: "st.custom.sonos.unmuted"
        state "nochime", label: 'No Chime', action: "togglechime", icon: "st.custom.sonos.muted"
      }
      standardTile("autobypass", "capability.momentary", width: 2, height: 2, title: "Auto-Bypass", decoration:"flat"){
        state "autobypass", label: 'Auto-Bypass', action: "autobypass", icon: "st.locks.lock.unlocked"
      }
      standardTile("bypassoff", "capability.momentary", width: 2, height: 2, title: "Bypass Off", decoration:"flat"){
        state "bypassoff", label: 'Bypass Off', action: "bypassoff", icon: "st.locks.lock.locked"
      }
      standardTile("instant", "capability.momentary", width: 2, height: 2, title: "Instant", decoration:"flat"){
        state "instant", label: 'Instant', action: "instant", icon: "st.Health & Wellness.health7", backgroundColor: "#00FF00"
      }
      standardTile("night", "capability.momentary", width: 2, height: 2, title: "Night", decoration:"flat"){
        state "night", label: 'Night', action: "night", icon: "st.Bedroom.bedroom2", backgroundColor: "#AA00FF"
      }
      standardTile("reset", "capability.momentary", width: 2, height: 2, title: "Sensor Reset", decoration:"flat"){
        state "reset", label: 'Sensor Reset', action: "reset", icon: "st.alarm.smoke.smoke"
      }
      valueTile("ledready", "device.ledready", width: 2, height: 1){
        state "ledready", label:'Ready: ${currentValue}'
      }
      valueTile("ledarmed", "device.ledarmed", width: 2, height: 1){
        state "ledarmed", label:'Armed: ${currentValue}'
      }
      valueTile("ledmemory", "device.ledmemory", width: 2, height: 1){
        state "ledmemory", label:'Memory: ${currentValue}'
      }
      valueTile("ledbypass", "device.ledbypass", width: 2, height: 1){
        state "ledbypass", label:'Bypass: ${currentValue}'
      }
      valueTile("ledtrouble", "device.ledtrouble", width: 2, height: 1){
        state "ledtrouble", label:'Trouble: ${currentValue}'
      }
      valueTile("ledprogram", "device.ledprogram", width: 2, height: 1){
        state "ledprogram", label:'Program: ${currentValue}'
      }
      valueTile("ledfire", "device.ledfire", width: 2, height: 1){
        state "ledfire", label:'Fire: ${currentValue}'
      }
      valueTile("ledbacklight", "device.ledbacklight", width: 2, height: 1){
        state "ledbacklight", label:'Backlight: ${currentValue}'
      }
      standardTile("key", "device.key", width: 2, height: 2, title: "Key", decoration:"flat"){
        state "nokey", label: 'Alarm Keys Off', action: "key", icon: "st.illuminance.illuminance.dark", defaultState: true
        state "key", label: 'Alarm Keys On', action: "nokey", icon: "st.illuminance.illuminance.light"
      }
      standardTile("keyfire", "device.keyfire", width: 2, height: 2, title: "Fire Key", decoration:"flat"){
        state "restore", label: 'Fire Key', action: "keyfire", icon: "st.Home.home29", backgroundColor: "#FF2400"
        state "alarm", label: 'Fire Key Alarm', action: "keyfire", icon: "st.Home.home29", backgroundColor: "#FF2400"
      }
      standardTile("keyaux", "device.keyaux", width: 2, height: 2, title: "Aux Key", decoration:"flat"){
        state "restore", label: 'Aux Key', action: "keyaux", icon: "st.Transportation.transportation7", backgroundColor: "#DD0000"
        state "alarm", label: 'Aux Key Alarm', action: "keyaux", icon: "st.Transportation.transportation7", backgroundColor: "#DD0000"
      }
      standardTile("keypanic", "device.keypanic", width: 2, height: 2, title: "Panic Key", decoration:"flat"){
        state "restore", label: 'Panic Key', action: "keypanic", icon: "st.Transportation.transportation9", backgroundColor: "#000fd5"
        state "alarm", label: 'Panic Key Alarm', action: "keypanic", icon: "st.Transportation.transportation9", backgroundColor: "#000fd5"
      }

      main "status"
      details(["status", "away", "stay", "disarm", "instant", "night", "refresh", "chime", "autobypass", "bypassoff", "reset", "ledready", "ledarmed", "ledmemory", "ledbypass", "key", "ledtrouble", "ledprogram", "ledfire", "ledbacklight", "keyfire", "keyaux", "keypanic"])
    }
}

def partition(String state, String partition, Map parameters) {
  // state will be a valid state for the panel (ready, notready, armed, etc)
  // partition will be a partition number, for most users this will always be 1

  log.debug "Partition: ${state} for partition: ${partition}"

  def onList = ['alarm','away','entrydelay','exitdelay','instantaway']

  def chimeList = ['chime','nochime']

  def troubleMap = [
    'trouble':"detected",
    'restore':"clear",
  ]
  
  def altState = ""
  altState=getPrettyName().get(state)

  if (onList.contains(state)) {
    sendEvent (name: "switch", value: "on")
  } else if (!(chimeList.contains(state) || troubleMap[state] || state.startsWith('led') || state.startsWith('key'))) {
    sendEvent (name: "switch", value: "off")
  }

  if (troubleMap[state]) {
    def troubleState = troubleMap."${state}"
    // Send trouble event
    sendEvent (name: "trouble", value: "${troubleState}")
    sendEvent (name: "partitionStatus", value: "Trouble: ${troubleState}")
  } else if (chimeList.contains(state)) {
    // Send chime event
    sendEvent (name: "chime", value: "${state}")
  } else if (state.startsWith('led')) {
    def flash = (state.startsWith('ledflash')) ? 'flash ' : ''
    for (p in parameters) {
      sendEvent (name: "led${p.key}", value: "${flash}${p.value}")
    }
  } else if (state.startsWith('key')) {
    def name = state.minus('alarm').minus('restore')
    def value = state.replaceAll(/.*(alarm|restore)/, '$1')
    sendEvent (name: "${name}", value: "${value}")
  } else {
    // Send final event
    sendEvent (name: "status", value: "${state}")
    sendEvent (name: "partitionStatus", value: "${altState}")
    sendEvent (name: "partitionCommand", value: "Select command", descriptionText: "${state}")
  }
}

def away() {
  parent.sendUrl("arm?part=${device.deviceNetworkId[-1]}")
}

def autobypass() {
  parent.autoBypass()
}

def bypassoff() {
  parent.sendUrl("bypass?zone=0&part=${device.deviceNetworkId[-1]}")
}

def disarm() {
  parent.sendUrl("disarm?part=${device.deviceNetworkId[-1]}")
}

def instant() {
  parent.sendUrl("toggleinstant?part=${device.deviceNetworkId[-1]}")
}

def night() {
  parent.sendUrl("togglenight?part=${device.deviceNetworkId[-1]}")
}

def nokey() {
  sendEvent (name: "key", value: "nokey")
}

def on() {
  sendPartitionCommand("away")
  //away()
}

def off() {
  sendPartitionCommand("disarm")
  //disarm()
}

def key() {
  sendEvent (name: "key", value: "key")
}

def keyfire() {
  if ("${device.currentValue("key")}" == 'key') {
    parent.sendUrl('panic?type=1')
  }
}

def keyaux() {
  if ("${device.currentValue("key")}" == 'key') {
    parent.sendUrl('panic?type=2')
  }
}

def keypanic() {
  if ("${device.currentValue("key")}" == 'key') {
    parent.sendUrl('panic?type=3')
  }
}

def refresh() {
  parent.sendUrl('refresh')
}

def reset() {
  parent.sendUrl("reset?part=${device.deviceNetworkId[-1]}")
}

def stay() {
  parent.sendUrl("stayarm?part=${device.deviceNetworkId[-1]}")
}

def togglechime() {
  parent.sendUrl("togglechime?part=${device.deviceNetworkId[-1]}")
}

def setPartitionCommand(String state) {
  log.debug "Processing command: ${state}"
  sendPartitionCommand(state)
}

def sendPartitionCommand(String state) {
  def altState = ""
  altState=getPrettyName().get(state)
  
  sendEvent (name: "partitionStatus", value: "${altState}")
  sendEvent (name: "partitionCommand", value: "${state}", descriptionText: "Command: ${state}")
      
  if (state =="away") { away() }
  else if (state == "autobypass") { autobypass() }
  else if (state == "bypassoff") { bypassoff() }
  else if (state == "disarm") { disarm() }
  else if (state == "instant") { instant() }
  else if (state == "night") { night() }
  else if (state == "nokey") { nokey () }
  else if (state == "key") { key() }
  else if (state == "keyfire") { keyfire() }
  else if (state == "keyaux") { keyaux() }
  else if (state == "keypanic") { keypanic() }
  else if (state == "reset") { reset() }
  else if (state == "stay") { stay() }
  else if (state == "chime") { togglechime() }
}

def getPrettyName() {
	return [
    	ready: "Ready",
      	notready: "Not Ready",
      	stay: "Armed Stay",
		away: "Armed Away",
		alarmcleared: "Alarm Cleared",
      	instant: "Armed Instant",
      	night: "Armed Night",
        disarm: "Disarming",
      	exitdelay: "Exit Delay",
        entrydelay: "Entry Delay",
        chime: "Toggling Chime",
      	bypassoff: "Sending Bypass Off",
      	keyfire: "Sending Fire Alert",
        keyaux: "Sending Aux Alert",
      	keypanic: "Sending Panic Alert"
	]
}    
