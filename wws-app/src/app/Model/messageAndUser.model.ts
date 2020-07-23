import { Message } from './message';
import { User } from './user.model';

export class MessageAndUser {
    message: Message;
    user: User;
    toUserId: string;
}