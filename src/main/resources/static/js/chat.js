var stompClient = null;
function connect() {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);
}

function onConnected() {
    stompClient.subscribe('/topic/public', onMessageReceived);
    stompClient.send("/app/chat.addUser",
        {},
        JSON.stringify({sender: username, type: 'JOIN', roomName: roomName})
    );
    console.log('Connected: ' + username);
    loadInitialMessages();
}

function onError(error) {
    console.log('Could not connect to WebSocket server. Please refresh this page to try again!');
    console.error('STOMP error:', error);
}

function sendMessage() {
    var messageInput = document.getElementById('message-input');
    var messageContent = messageInput.value.trim();
    if (messageContent && stompClient) {
        var chatMessage = {
            sender: username,
            content: messageContent,
            type: 'CHAT',
            roomName: roomName,
            timestamp: new Date().toISOString()
        };
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    displayMessage(message);
}

function displayMessage(message) {
    var chatMessages = document.getElementById('chat-messages');
    var messageElement = document.createElement('div');
    messageElement.classList.add('message-item');

    if (message.sender === username) {
        messageElement.classList.add('own-message');
    } else {
        messageElement.classList.add('other-message');
    }

    var headerElement = document.createElement('div');
    headerElement.classList.add('message-header');

    var profileImageElement = document.createElement('img');
    profileImageElement.classList.add('profile-image');
    // 여기서 사용자의 프로필 이미지를 가져오는 함수를 호출합니다
    getProfileImage(message.sender).then(imageUrl => {
        profileImageElement.src = imageUrl || 'https://i.pinimg.com/474x/05/19/c6/0519c68aab5028942b31d861916f32f8.jpg';
    });
    profileImageElement.alt = message.sender;

    var senderNameElement = document.createElement('span');
    senderNameElement.classList.add('sender-name');
    senderNameElement.textContent = message.sender;

    headerElement.appendChild(profileImageElement);
    headerElement.appendChild(senderNameElement);

    var contentElement = document.createElement('div');
    contentElement.classList.add('message-content');
    contentElement.textContent = message.content;

    var timestampElement = document.createElement('div');
    timestampElement.classList.add('message-timestamp');
    timestampElement.textContent = formatTimestamp(message.timestamp);

    messageElement.appendChild(headerElement);
    messageElement.appendChild(contentElement);
    messageElement.appendChild(timestampElement);

    chatMessages.appendChild(messageElement);
    chatMessages.scrollTop = chatMessages.scrollHeight;
}

function getProfileImage(username) {
    return fetch(`/${username}/profile-image`)
        .then(response => response.json())
        .then(data => data.profileImageUrl)
        .catch(() => null);
}

function formatTimestamp(timestamp) {
    var date = new Date(timestamp);
    return date.toLocaleString('ko-KR', {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit',
        hour: '2-digit',
        minute: '2-digit',
        hour12: false
    });
}

function loadInitialMessages() {
    if (initialMessages && Array.isArray(initialMessages)) {
        initialMessages.forEach(displayMessage);
    }
}

// DOM이 로드된 후 초기화 함수 실행
document.addEventListener('DOMContentLoaded', function() {
    // 엔터 키 이벤트 리스너 추가
    document.getElementById('message-input').addEventListener('keypress', function(e) {
        if (e.key === 'Enter') {
            e.preventDefault(); // 폼 제출 방지
            sendMessage();
        }
    });

    // 연결 시작 및 초기 메시지 로드
    connect();
    loadInitialMessages();
});