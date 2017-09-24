package com.ofhi.common.Listener;


import com.ofhi.common.cache.shiro.CachingShiroSessionDao;
import com.ofhi.common.cache.shiro.ShiroSessionService;
import lombok.Setter;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroSessionListener implements SessionListener {

    private Logger log = LoggerFactory.getLogger(ShiroSessionListener.class);

    @Setter
    private ShiroSessionService shiroSessionService;

    @Setter
    private CachingShiroSessionDao sessionDao;

    @Override
    public void onStart( final Session session) {
        // 会话创建时触发
        log.info("session {} onStart", session.getId());
    }

    @Override
    public void onStop( final Session session) {
        sessionDao.delete(session);
        shiroSessionService.sendUnCacheSessionMessage(session.getId());
        log.info("session {} onStop", session.getId());
    }

    @Override
    public void onExpiration( final Session session) {
        sessionDao.delete(session);
        shiroSessionService.sendUnCacheSessionMessage(session.getId());
        log.info("session {} onExpiration", session.getId());
    }

}