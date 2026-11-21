package org.espetro.client.gui;

/**
 * 兼容入口：教程已迁至 {@link TutorialClientController} + {@link TutorialHudOverlay}，
 * 按 Enter 进入下一步。
 */
public final class TutorialOverlay {

    private TutorialOverlay() {
    }

    public static void show(String newStepId, int newIndex, int newTotal, boolean newAllowSkip) {
        TutorialClientController.show(newStepId, newIndex, newTotal, newAllowSkip);
    }

    public static void clear() {
        TutorialClientController.clear();
    }

    public static void tick() {
        TutorialClientController.tick();
    }

    public static boolean isVisible() {
        return TutorialClientController.isActive();
    }

    public static String getStepId() {
        return TutorialClientController.getStepId();
    }
}
