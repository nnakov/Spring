package services;

import model.Comment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import proxies.CommentNotificationProxy;
import repositories.CommentRepository;

@Component
public class AnotherService {

    private final CommentRepository dbCommentRepository;
    private final CommentNotificationProxy commentPushNotificationProxy;

    public AnotherService(CommentRepository dbCommentRepository, @Qualifier("PUSH") CommentNotificationProxy commentPushNotificationProxy) {
        this.dbCommentRepository = dbCommentRepository;
        this.commentPushNotificationProxy = commentPushNotificationProxy;
    }

    public void publishComment(Comment comment) {
        dbCommentRepository.storeComment(comment);
        commentPushNotificationProxy.sendComment(comment);
    }
}
