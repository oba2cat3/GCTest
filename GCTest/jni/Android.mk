LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := gc_test
LOCAL_SRC_FILES := gc_test.cpp

include $(BUILD_SHARED_LIBRARY)
