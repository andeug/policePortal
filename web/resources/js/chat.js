
var bodycont = '<button class="open-button" onclick="openChat()"><i class="fas fa-comment-alt" style="font-size:36px;color:#255ba8"></i></button> \n\
<div id="parent" class="perentStyle"> \n\
<button type="button" class="mycancel" onclick="closeChat()"><i class="fa fa-close" style="font-size:14px;"></i></button>\n\
<div class="chat-popup" id="myForm" style="display: block">\n\
<form class="form-container" id="chatForm"  method="post" onsubmit="return false;" >\n\
<div class="formTitle"> Welcome to MSpace Solutions Limited. How can we help you today?</div><br>\n\
<div class="nameinput">\n\
<label for="name">\n\
<span>Your Name:</span>\n\
<span style="color: red">*</span>\n\
</label><br>\n\
<input id="name" name="name" autocomplete="on" class="inputel" placeholder="Your name..."required="true">\n\
</div><div class="phoneinput"><label for="phone">\n\
<span>Phone Number:</span><span style="color: red">*</span></label><br>\n\
<input id="phone" name="phone" autocomplete="on" class="inputel" placeholder="Your phone..."type="number" required="true">\n\
</div><div class="emlinput">\n\
<label for="email"> <span>Your Email:</span><span style="color: red">*</span></label><br>\n\
<input type="email" name="email" id="email" autocomplete="on" class="inputel" placeholder="Your email..."required="true">\n\
</div><br>\n\
<input type="submit" id="submit" class="btn" value="Start Chat" onclick="openChatBox()">\n\
</form></div><div id="maindiv" class="log"><div id="log"> </div>\n\
<div id="smsctrl" class="smsctrl"> \n\
<textarea spellcheck="true" placeholder="Type Message Here" id="msg_txt" class="txt" rows="4" style="resize: none" required></textarea>\n\
<button id="send" class="sendbtn"><i class="fa fa-send-o"></i></button>\n\
</div></div></div>';
        //window.onload = function(){
        var body = document.getElementsByTagName('body')[0];
        body.innerHTML += bodycont;
        var tx = document.getElementById('msg_txt');
        tx.setAttribute('style', 'height:' + (50 + this.scrollHeight) + 'px;overflow-y:hidden;');
        tx.addEventListener("input", OnInput, false);
        function OnInput() {
        this.style.height = '50px';
                this.style.height = (this.scrollHeight) + 'px';
        }
//};
function openChat() {
document.getElementById("parent").style.display = "block";
        document.getElementById("myForm").style.display = "block";
        document.getElementById("maindiv").style.display = "none";
}

function openChatBox() {
document.getElementById("parent").style.display = "block";
        document.getElementById("maindiv").style.display = "block";
        document.getElementById("myForm").style.display = "none";
        startChat();
        }

var sock;
        var name;
        var phone;
        var email;
        var chatid;
        function startChat() {

        sock = new WebSocket("ws://192.168.1.56:5001");
                name = document.getElementById('name').value;
                phone = document.getElementById('phone').value;
                email = document.getElementById('email').value;
                var d = new Date();
                chatid = Math.floor(Math.random() * 1001) + "" + d.getTime();
                var log = document.getElementById('log');
                sock.onopen = function (event) {
//                alert("socket connected successfully");
//                setTimeout(function () {
//                    sock.send("hey there");
//                }, 1000);



                sock.send(JSON.stringify({
                type: "name",
                        userid: chatid,
                        data: name,
                        phone:phone,
                        email:email
                }));
                };
                sock.onmessage = function (event) {

                console.log(event);
                        var json = JSON.parse(event.data);
                        if (json.type === "message") {

                log.innerHTML += line2("<i>" + json.name + "</i><br> " + json.data, "chat admin") + "<br>";
                        log.scrollTop = log.scrollHeight; //auto-scrolls to the latest message
                }

                };
                function line2(messageReply, styleClass, time) {
                var date = new Date();
                        var amOrPm = (date.getHours() < 12) ? "AM" : "PM";
                        var hour = (date.getHours() < 12) ? date.getHours() : date.getHours() - 12;
                        txt_time = hour + ':' + ('0' + date.getMinutes()).slice( - 2) + ' ' + amOrPm;
                        var line2 = '<div class="chatlogs"><div class="' + styleClass + '"><p class="chat-message">' + messageReply + '<br><span class="time-left" style="font-size:10px;">' + txt_time + '&#10004;</span></p></div>';
                        return line2;
                }
        ;
                document.getElementById('send').onclick = function () { sendMessage(); }

        function sendMessage() {
        var text = document.getElementById('msg_txt').value;
                if (text !== null && text.length !== 0) {
        sock.send(
                JSON.stringify({type: "message", data: text, recipient: "mspace_chatAdmin"})
                );
                log.innerHTML += line2(text, "chat client") + "<br>";
                log.scrollTop = log.scrollHeight; //auto-scrolls to the latest message
                document.getElementById('msg_txt').value = "";
                document.getElementById('msg_txt').style.height = '50px';
        }

        }
        $(document).on('keypress', '#msg_txt', function (e) {
        if (e.which === 13) {
        sendMessage();
                e.preventDefault();
        }
        });
        }

function closeChat() {
document.getElementById("parent").style.display = "none";
        sock.close();
        };

